package br.com.miqueias_oliveira.sistemabancario;

import br.com.miqueias_oliveira.sistemabancario.model.Conta;
import br.com.miqueias_oliveira.sistemabancario.service.ContaService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ContaService contaService = new ContaService();

        System.out.print("Digite o nome do titular da Conta: ");
        String nomeTitular = sc.nextLine();
        System.out.println();

        Conta novaConta = contaService.criarConta(nomeTitular);

        System.out.println("Conta criada com sucesso!");
        System.out.println("Número da conta: " + novaConta.getNumero());
        System.out.println("Titular: " + novaConta.getTitular());
        System.out.println("Saldo inicial: R$ " + novaConta.getSaldo());

        sc.close();
    }
}