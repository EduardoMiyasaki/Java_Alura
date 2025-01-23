import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.print("Informe seu nome:");
        String nome = teclado.nextLine();

        System.out.print("Informe o tipo da sua conta:");
        String tipoConta = teclado.nextLine();

        System.out.println("Informe o seu saldo atual:");
        double saldo = teclado.nextDouble();

        System.out.println(String.format("""
                ***************************************
                Dados iniciais do cliente:
                                
                Nome: %s
                Tipo de Conta: %s 
                Saldo Inicial: %.2f               
                ***************************************
                """, nome, tipoConta, saldo));


        while (true) {

            System.out.println("""
                    Operações
                                    
                    1 - Consultador saldo
                    2 - Receber valor
                    3 - Transferir valor
                    4 - Sair
                                    
                    Digite a opção desejada:
                    """);

            int opcao = teclado.nextInt();

            switch (opcao) {

                case 1: {
                    System.out.println("O saldo atual é R$ " + saldo);
                    break;
                }

                case 2: {
                    System.out.print("Informe o valor a receber:");
                    saldo = saldo + teclado.nextDouble();
                    System.out.println("Saldo atualizado R$ " + saldo);
                    break;
                }

                case 3: {
                    System.out.print("Informe o valor que deseja tranferir:");
                    double valorSaque = teclado.nextDouble();

                    if (valorSaque > saldo) {
                        System.out.println("Não há saldo suficiente para fazer essa transferência");
                    } else {
                        saldo -= valorSaque;
                        System.out.println("Saldo atualizado R$" + saldo);
                    }
                    break;
                }

                case 4: {
                    System.out.println("Encerrando o programa. Obrigado por usar nosso sistema!");
                    teclado.close();
                    System.exit(0);
                }


                default: {
                    System.out.println("Opção inválida");
                }
            }


        }
    }
}
