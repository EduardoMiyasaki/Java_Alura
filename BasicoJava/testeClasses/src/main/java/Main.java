public class Main {
    public static void main(String[] args) {

        Pessoa pessoa1 = new Pessoa();

        pessoa1.setNome("Eduardo");
        pessoa1.setIdade(18);
        pessoa1.setCidade("Itaqua");

        System.out.println(pessoa1.mostrarDados());

        System.out.println(pessoa1.mostrarDados("Eduardo"));
    }
}
