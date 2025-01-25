import br.com.alura.screenmatch.model.Movie;

public class DeclarandoArrays {

    public static void main(String[] args) {

        // IMPORTANTE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // Não usar array e sim ArrayList

        // array você tem que definir um valor fixo que é o tamanho desse array
        // Ausência de metódos de inseção, procura ou remoção
        int[] numeros = new int[5];

        numeros[0] = 1;

        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = i + 1;
        }

        Movie[] filmes = new Movie[2];

        Movie filme1 = new Movie("Avatar" , 2009);
        Movie filme2 = new Movie("DogVille" , 2003);

//        filme1.setAnoDeLancamento(2009);
//        filme2.setAnoDeLancamento(2003);

        filmes[0] = filme1;
        filmes[1] = filme2;


    }
}
