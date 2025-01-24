import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Pessoa> listaPessoas = new ArrayList<>();

        Pessoa pessoa1 = new Pessoa();
        Pessoa pessoa2 = new Pessoa();
        Pessoa pessoa3 = new Pessoa();

        pessoa1.setNome("Eduardo");
        pessoa1.setIdade(18);

        pessoa2.setNome("Fernando");
        pessoa2.setIdade(13);

        pessoa3.setNome("Rita");
        pessoa3.setIdade(17);

        listaPessoas.add(pessoa1);
        listaPessoas.add(pessoa2);
        listaPessoas.add(pessoa3);

        System.out.println("Total de pessoas: " + listaPessoas.size());
        System.out.println("A 1° pessoa da lista se chama " + listaPessoas.get(0).getNome());
        System.out.println(listaPessoas);
    }
}
