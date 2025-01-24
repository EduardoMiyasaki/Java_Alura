import Exercicio2.Cachorro;
import Exercicio2.Gato;

public class Main {

    public static void main(String[] args) {

        Gato gato = new Gato();

        System.out.println(gato.emitirSom());
        System.out.println(gato.arranharImoveis());

        Cachorro dog = new Cachorro();

        System.out.println(dog.abanarRabo());
        System.out.println(dog.emitirSom());
    }
}
