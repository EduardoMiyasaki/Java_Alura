package Main;

import br.com.alura.models.MinhasPreferidas;
import br.com.alura.models.Musica;
import br.com.alura.models.PodCast;

public class Main {

    public static void main(String[] args) {

        Musica musica1 = new Musica();

        musica1.setTitulo("Forever");
        musica1.setCantor("KISS");

        for (int i = 0; i < 150; i++) {
            musica1.reproduzir();
        }

        for (int i = 0; i < 15; i++) {
            musica1.curtir();
        }

        PodCast podcast1 = new PodCast();

        podcast1.setApresentador("Igão");
        podcast1.setConvidado("Mitico");

        for (int i = 0; i < 10; i++) {
            podcast1.reproduzir();
        }

        for (int i = 0; i < 1; i++) {
            podcast1.curtir();
        }

        MinhasPreferidas pref1 = new MinhasPreferidas();
        pref1.incluir(musica1);
        pref1.incluir(podcast1);
    }
}
