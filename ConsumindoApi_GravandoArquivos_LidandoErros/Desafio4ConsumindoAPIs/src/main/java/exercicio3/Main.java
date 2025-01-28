package exercicio3;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) {

        Gson gson = new GsonBuilder().
                setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).
                setPrettyPrinting().
                create();

        Veiculo carro1 = new Veiculo("Onix",4);

        System.out.println(gson.toJson(carro1));
    }
}
