import java.io.FileWriter;
import java.io.IOException;

public class GeradorArquivo {

    public FileWriter gerarArquivo() throws IOException {
        return new FileWriter("dadosEnderecos.json");
    }
}
