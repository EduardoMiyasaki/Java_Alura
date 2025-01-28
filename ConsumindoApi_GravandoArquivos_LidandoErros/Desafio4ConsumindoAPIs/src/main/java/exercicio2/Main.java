package exercicio2;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) {

        Gson gson = new GsonBuilder().
                setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        Titulo titulo1 = new Titulo("Matrix" , "Jack daniels" , 180);

        System.out.println(gson.toJson(titulo1));
    }
}
