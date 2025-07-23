package br.com.miqueias_oliveira.sistemabancario;

import br.com.miqueias_oliveira.sistemabancario.model.Conta;
import br.com.miqueias_oliveira.sistemabancario.service.ContaService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ContaService contaService = new ContaService();
        Conta conta = null;

        int opcao;

        do {
            System.out.println();
            System.out.println("=====| MENU BANCO |=====");
            System.out.println();
            System.out.println("1 - Criar conta");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Ver saldo");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch  (opcao) {
                case 1:
                    if (conta == null) {
                        System.out.println();
                        System.out.print("Digite o nome do titular: ");
                        String nome = sc.nextLine();
                        conta = contaService.criarConta(nome);
                        System.out.println();
                        System.out.println("Conta criada com sucesso!");
                        System.out.println("Número: " + conta.getNumero());
                    } else {
                        System.out.println();
                        System.out.println("======================================");
                        System.out.println("Você ja criou uma conta anteriormente!");
                        System.out.println("======================================");
                    }
                    break;

                case 2:
                    if (conta == null) {
                        System.out.println();
                        System.out.println("======================================");
                        System.out.println("-----> Crie uma conta primeiro <------");
                        System.out.println("======================================");
                    } else {
                        System.out.println();
                        System.out.print("Digite o valor para depositar: ");
                        double valor = sc.nextDouble();

                        boolean sucesso = conta.depositar(valor); // vai dar erro caso o usuário tentar depositar 0.

                        System.out.println();
                        if (sucesso) {
                            System.out.println("Depósito realizado com sucesso.");
                        } else {
                            System.out.println("================================================");
                            System.out.println("Valor inválido! O valor deve ser maior que zero.");
                            System.out.println("================================================");
                        }
                    }
                    break;

                case 3:
                    if (conta == null) {
                        System.out.println();
                        System.out.println("======================================");
                        System.out.println("-----> Crie uma conta primeiro <------");
                        System.out.println("======================================");
                    } else {
                        System.out.println();
                        System.out.print("Digite o valor para sacar: ");
                        double valor = sc.nextDouble();
                        System.out.println();

                        if (valor <= 0) {
                            System.out.println("========================================");
                            System.out.println("-----> Valor inválido para saque <------");
                            System.out.println("========================================");
                        } else {
                            boolean sucesso = conta.sacar(valor);

                            if (sucesso) {
                                System.out.println("Saque realizado com sucesso.");
                            } else {
                                System.out.println("======================================");
                                System.out.println("--------> Saldo insuficiente <--------");
                                System.out.println("======================================");
                            }
                        }
                    }
                    break;

                case 4:
                    if (conta == null) {
                        System.out.println();
                        System.out.println("======================================");
                        System.out.println("-----> Crie uma conta primeiro <------");
                        System.out.println("======================================");
                    } else {
                        System.out.println();
                        System.out.print("Saldo atual: R$ " + conta.getSaldo());
                        System.out.println();
                    }
                    break;

                case 5:
                    System.out.println();
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println();
                    System.out.println("======================================");
                    System.out.println("----------> Opção inválida <----------");
                    System.out.println("======================================");
            }
        } while ( opcao != 5);

        sc.close();
    }
}