package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PaymentProcessor processor = new PaymentProcessor(null);

        while (true){
            System.out.println("\nEscolha a forma de pagamento: \n"+
                    "1 - Pix\n" +
                    "2 - Cartão de Crédito\n" +
                    "3 - Boleto Bancário\n" +
                    "0 - Sair\n");

            System.out.println("Opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            if(opcao == 0){
                System.out.println("Encerrando!");
                break;
            }

            PaymentStrategy estrategia = null;

            switch (opcao){
                case 1:
                    estrategia = new PixPayment();
                    break;
                case 2:
                    estrategia = new CreditCardPayment();
                    System.out.println("Insira o número do cartão: ");
                    String numeroCartao = sc.nextLine();
                    ((CreditCardPayment) estrategia).coletarNumCartao(numeroCartao);
                    break;
                case 3:
                    estrategia = new BoletoPayment();
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    continue;
            }

            processor.definirMetodo(estrategia);

            System.out.println("Valor da transação: ");
            double valor = sc.nextDouble();
            sc.nextLine();

            processor.processar(valor);
        }

        sc.close();
    }
}