public enum Moeda {

    DOLAR(5.77),
    EURO(6.06);

    private double conversao;

    Moeda(double valor) {
        this.conversao = valor;
    }

    public double converterPara(double valorEmReais) {
        return valorEmReais / conversao;
    }
}
