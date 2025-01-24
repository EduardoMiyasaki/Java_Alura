public class Carro {

    String modelo;
    int ano;
    String cor;

    String exibirFicha() {
        return "O nome do carro é" + this.modelo + " o nome do carro é " + this.ano;
    }

    int calcularIdadeCarro(){
        return 2024 - ano;
    }
}
