public class Main {

    public static void main(String[] args) {

        // Switch Case =
        // É uma forma mais simplificada e legível de escrever vários blocos
        // if/else encadeados.

        int diaDaSemana = 4;
        String nomeDoDia = null;

        switch (diaDaSemana) {
            case 1:
                nomeDoDia = "Domingo";
            break;
            case 2:
                nomeDoDia = "Segunda-Feira";
                break;
            case 3:
                nomeDoDia = "Terça-Feira";
                break;
            case 4:
                nomeDoDia = "Quarta-Feira";
                break;
            case 5:
                nomeDoDia = "Quinta-Feira";
                break;
            case 6:
                nomeDoDia = "Sexta-Feira";
                break;
            case 7:
                nomeDoDia = "Sábado";
                break;
        }

        System.out.println("O dia " + diaDaSemana + " é " + nomeDoDia);
    }
}
