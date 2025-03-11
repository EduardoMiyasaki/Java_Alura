
package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.repository.EpisodioRepository;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import br.com.alura.screenmatch.validacao.ValidacaoException;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    private SerieRepository serieRepository;
    private EpisodioRepository episododioRepository;

    private List<Serie> listaSeries = new ArrayList<>();

    private Optional<Serie> serieBusca;

    public Principal(SerieRepository serieRepository, EpisodioRepository episododioRepository) {
        this.serieRepository = serieRepository;
        this.episododioRepository = episododioRepository;
    }

    public void exibeMenu() {
        var opcao = 1;
        var menu = """
                1 - Buscar séries
                2 - Buscar episódios
                3 - Listar séries buscadas
                4 - Procurar série pelo nome
                5 - Buscar Séries pelo ator
                6 - Buscar Séries pelo ator e avaliação
                7 - Buscar as 5 melhores séries
                8 - Buscar por categoria
                9 - Buscar por Total de Temporadas e Avaliação
                10 - Buscar episódio por trecho
                11 - Listar episódios por trecho da série específica
                12 - Buscar os 5 melhores episódios de uma série
                13 - Buscar episódios a partir de uma data
                0 - Sair""";

        while (opcao != 0) {
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 4:
                    buscarSeriePeloNome();
                    break;
                case 5:
                    buscarSeriesPeloAtor();
                    break;
                case 6:
                    buscarSeriesPeloAtorAndAvaliacao();
                    break;
                case 7:
                    buscarMelhoresSeries();
                    break;
                case 8:
                    buscarPorCategoria();
                    break;
                case 9:
                    buscarPorTotalTemporadasAndAvaliacao();
                    break;
                case 10:
                    buscarPorEpisodioPorTrecho();
                    break;
                case 11:
                    buscarEpisodiosDeSerie();
                    break;
                case 12:
                    MelhoresEpisodiosDeUmaSerie();
                    break;
                case 13:
                    BuscarEpisodioDepoisDeUmaData();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        serieRepository.save(new Serie(dados));
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.converterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie() {
        listarSeriesBuscadas();

        System.out.println("Informe a série que você deseja buscar");
        var nomeSerie = leitura.nextLine();

        Optional<Serie> serie = serieRepository.findByTituloContainingIgnoreCase(nomeSerie);
//      Optional<Serie> serie = listaSeries.stream().filter(s -> s.getTitulo().toLowerCase().contains(nomeSerie.toLowerCase())).findFirst();

        if (serie.isPresent()) {

            var serieBuscada = serie.get();
            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieBuscada.getTotalTemporadas(); i++) {
                var json = consumo.obterDados(ENDERECO + serieBuscada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DadosTemporada dadosTemporada = conversor.converterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);

            List<Episodio> listaEpisodios = temporadas.stream()
                    .flatMap(t -> t.episodios()
                            .stream()
                            .map(e -> new Episodio(t.numero(), e)))
                    .collect(Collectors.toList());

            serieBuscada.setEpisodios(listaEpisodios);

            serieRepository.save(serieBuscada);
        } else {
            System.out.println("Série não encontrada!!!!!!!!!");
        }
    }

    private void listarSeriesBuscadas() {
        listaSeries = serieRepository.findAll();

        listaSeries.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    private void buscarSeriePeloNome() {

        System.out.println("Insira o nome da Série que deseja buscar: ");
        String seriePeloNome = leitura.nextLine();

        serieBusca = serieRepository.findByTituloContainingIgnoreCase(seriePeloNome);

        if (serieBusca.isPresent()) {
            System.out.println(serieBusca);
        } else {
            System.out.println("Série não encontrada");
        }
    }

    private void buscarSeriesPeloAtor() {
        System.out.println("Insira o nome do ator que deseja buscar: ");
        String nomeAtor = leitura.nextLine();

        List<Serie> seriesEncontradasPeloAtor = serieRepository.findByAtoresContainingIgnoreCase(nomeAtor);
        System.out.println("Séries em que o " + nomeAtor + " trabalhou");
        seriesEncontradasPeloAtor.forEach(s ->
                System.out.println("Nome da série: " + s.getTitulo() + "\nAtores: " + s.getAtores()));
    }

    private void buscarSeriesPeloAtorAndAvaliacao() {
        System.out.println("Insira o nome do ator que deseja buscar: ");
        String nomeAtor = leitura.nextLine();

        System.out.println("Insira a nota miníma que deseja ver");
        double nota = leitura.nextDouble();

        List<Serie> seriesEncontradasPeloAtor = serieRepository.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor, nota);
        System.out.println("Séries em que o " + nomeAtor + " trabalhou");
        seriesEncontradasPeloAtor.forEach(s ->
                System.out.println("Nome da série: " + s.getTitulo() + "\nAtores: " + s.getAtores() + "\nAvaliação: " + s.getAvaliacao()));

    }

    private void buscarMelhoresSeries() {

        List<Serie> melhoresSeries = serieRepository.findTop5ByOrderByAvaliacaoDesc();
        System.out.println("Cinco melhores séries");
        melhoresSeries.forEach(s ->
                System.out.println("Nome da série: " + s.getTitulo() + "\nAvaliação: " + s.getAvaliacao()));
    }

    private void buscarPorCategoria() {

        System.out.println("Informe de qual Gênero/Categoria você deseja buscar: ");
        String genero = leitura.nextLine();

        try {
            List<Serie> seriesPorCategoria = serieRepository.findByGenero(Categoria.fromString(genero));

            System.out.println("Séries da categoria: " + genero);
            seriesPorCategoria.forEach(s ->
                    System.out.println("Título: " + s.getTitulo() + "\nCategoria: " + s.getGenero()));
        } catch (IllegalArgumentException e) {
            throw new ValidacaoException(e.getMessage());
        }
    }

    private void buscarPorTotalTemporadasAndAvaliacao() {

        System.out.println("Deseja ver séries com até quantas temporadas: ");
        int totalTemporadas = leitura.nextInt();

        System.out.println("Infome o mínimo de avaliação");
        double avaliacaoMinima = leitura.nextDouble();

//        List<Serie> seriesEncontradas = serieRepository
//                .findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(totalTemporadas, avaliacaoMinima);

        List<Serie> seriesEncontradas = serieRepository.seriesPorTemporadaEAvaliacao(totalTemporadas, avaliacaoMinima);

        seriesEncontradas.forEach(s ->
                System.out.println("Título: " + s.getTitulo() + " Total de Temporadas: " + s.getTotalTemporadas() + " Avaliação :" + s.getAvaliacao()));
    }

    private void buscarPorEpisodioPorTrecho() {

        System.out.println("Informe o trecho do episódio");
        String trechoEpisodio = leitura.nextLine();

//        episododioRepository.listarEpisodiosPorTrecho(trechoEpisodio).forEach(System.out::println);
        List<Episodio> episodiosEncontrados = episododioRepository.listarEpisodiosPorTrecho(trechoEpisodio);

        episodiosEncontrados.forEach(System.out::println);

    }

    private void buscarEpisodiosDeSerie() {

        System.out.println("Informe o nome da série");
        String trechoSerie = leitura.nextLine();

        System.out.println("Informe o trecho do episódio");
        String trechoEpisodio = leitura.nextLine();

        List<Episodio> listaEpisodios = serieRepository.listarEpisodiosPorTrechoDaSerieEspecifica
                (trechoSerie, trechoEpisodio);

        listaEpisodios.forEach(e ->
                System.out.printf("Série: %s Temporada: %s Episódios %s - %s \n",
                        e.getSerie().getTitulo(), e.getTemporada(), e.getNumero(), e.getTitulo())
        );
    }

    private void MelhoresEpisodiosDeUmaSerie() {
        listarSeriesBuscadas();
        buscarSeriePeloNome();

        if (serieBusca.isPresent()) {
            List<Episodio> episodioList = serieRepository.listandoTop5EpisodiosDeUmaSerie(serieBusca);

            episodioList.forEach(e ->
                    System.out.printf("Série: %s Temporada: %s Episódios %s - %s Avaliação: %f \n",
                            e.getSerie().getTitulo(), e.getTemporada(), e.getNumero(), e.getTitulo(), e.getAvaliacao()));
        }
    }

    private void BuscarEpisodioDepoisDeUmaData() {
        listarSeriesBuscadas();
        buscarSeriePeloNome();

        if (serieBusca.isPresent()) {
            System.out.println("Informe o ano limite de de lançamento");
            int anoLancamento = leitura.nextInt();

            List<Episodio> episodiosAno = serieRepository.episodiosPorSerieAposAno(serieBusca, anoLancamento);
            episodiosAno.forEach(System.out::println);
        }
    }
}
