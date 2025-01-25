package exercicio2;

public class Main {
    public static void main(String[] args) {

        Animal animal1= new Cachorro();

        if (animal1 instanceof Cachorro) {
            System.out.println("Era um cachorro agora é só animal");
            Cachorro cachorro = (Cachorro) animal1;
        }
        else {
            System.out.println("Não é um cachorro");
        }
    }
}
