public class Condicional {

    public static void main(String[] args) {

        int anoDeLancamento = 2022;
        boolean incluidoNoPlano = false;
        double notaDoFilme = 8.1;

        String tipoPlano = "Plus";

        if (anoDeLancamento >= 2022) {
            System.out.println("Filme novo");
        } else {
            System.out.println("Filme retrô");
        }

       // condicional OU
        if (incluidoNoPlano || tipoPlano.equalsIgnoreCase("plus")) {
            System.out.println("Pode assistir esse filme");
        }
        else {
            System.out.println("Você deve pagar a alocação");
        }

        // condicional E
       if (incluidoNoPlano && tipoPlano.equalsIgnoreCase("plus")) {
            System.out.println("Pode assistir esse filme");
        }
        else {
            System.out.println("Você deve pagar a alocação");
        }


    }
}
