package Exercicio4;

public class ConversorTemperaturaPadrao implements ConversorTemperatura{

    double celsius;
    double fahrenheit;

    @Override
    public double celsiusParaFahrenheit() {
        return this.fahrenheit = (this.celsius * 1.8) + 32;
    }

    @Override
    public double fahrenheitParaCelsius() {
        return this.celsius = (this.fahrenheit - 32) * 5/9;
    }

    public static void main(String[] args) {

        ConversorTemperaturaPadrao conversor = new ConversorTemperaturaPadrao();

        conversor.celsius = 25;

        System.out.println(conversor.celsiusParaFahrenheit());
        System.out.println(conversor.fahrenheitParaCelsius());
    }
}
