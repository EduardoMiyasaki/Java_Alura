package Exercicio1;

public class ConversorMoeda implements ConversaoFinanceira{

    int carteiraDolar;

    public int getCarteiraDolar() {
        return carteiraDolar;
    }

    public void setCarteiraDolar(int carteiraDolar) {
        this.carteiraDolar = carteiraDolar;
    }

    @Override
    public double converterDolarParaReal() {
        return carteiraDolar * 5.90;
    }

    public static void main(String[] args) {
        ConversorMoeda conversor = new ConversorMoeda();

        conversor.setCarteiraDolar(10);

        System.out.println(conversor.converterDolarParaReal());
    }
}
