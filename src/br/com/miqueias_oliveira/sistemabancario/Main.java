package br.com.miqueias_oliveira.sistemabancario;

import br.com.miqueias_oliveira.sistemabancario.model.Conta;
import br.com.miqueias_oliveira.sistemabancario.repository.ContaRepository;
import br.com.miqueias_oliveira.sistemabancario.service.ContaService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ContaService contaService = new ContaService();
        ContaRepository contaRepository = new ContaRepository();

        int opcao;

        do {
            System.out.println();
            System.out.println("=====| MENU BANCO |=====");
            System.out.println();
            System.out.println("1 - Criar conta");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Transferir para outra conta");
            System.out.println("5 - Ver saldo");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            // Checagem para impedir depósito, saque ou transferência sem conta criada.
            if ((opcao == 2 || opcao == 3 || opcao == 4 || opcao == 5) && contaRepository.listarTodas().isEmpty()) {
                System.out.println();
                exibirMensagemCrieContaPrimeiro();
                continue; // Volta para o início do loop, mostrando as opções do menu de novo.
            }

            switch  (opcao) {
                case 1:
                    System.out.println();
                    System.out.println("Digite o nome do titular: ");
                    String nome = sc.nextLine();
                    Conta novaConta = contaService.criarConta(nome);
                    contaRepository.salvar(novaConta);
                    System.out.println();
                    System.out.println("Conta criada com sucesso!");
                    System.out.println("Número: " + novaConta.getNumero());
                    break;

                case 2:
                    System.out.println();
                    System.out.print("Digite o número da conta para depósito: ");
                    String numDep = sc.nextLine();

                    Conta contaDep = contaRepository.buscaPorNumero(numDep);
                    if (contaDep == null) {
                        System.out.println();
                        exibirMensagemErroContaNaoEncontrada();
                    } else {
                        System.out.print("Digite o valor para depositar: ");
                        double valor = sc.nextDouble();
                        sc.nextLine(); // Limpar buffer

                        boolean sucesso = contaDep.depositar(valor); // vai dar erro caso o usuário tentar depositar 0.

                        if (sucesso) {
                            System.out.println("Depósito realizado com sucesso.");
                        } else {
                            System.out.println();
                            exibirMensagemValorInvalidoDeposito();
                        }
                    }
                    break;

            case 3:
                System.out.println();
                System.out.print("Digite o número da conta para saque: ");
                String numSaque = sc.nextLine();
                Conta contaSaque = contaRepository.buscaPorNumero(numSaque);

                if (contaSaque == null) {
                    System.out.println();
                    exibirMensagemErroContaNaoEncontrada();
                } else {
                    System.out.print("Digite o valor para sacar: ");
                    double valor = sc.nextDouble();
                    sc.nextLine(); // limpar buffer

                    if (valor <= 0) {
                        System.out.println();
                        exibirMensagemValorInvalidoSaque();
                    } else {
                        boolean sucesso = contaSaque.sacar(valor);

                        if (sucesso) {
                            System.out.println("Saque realizado com sucesso.");
                        } else {
                            System.out.println();
                            exibirMensagemSaldoInsuficiente();
                        }
                    }
                }
                break;

                case 4:
                    System.out.println();
                    System.out.print("Digite o número da conta origem: ");
                    String numOrigem = sc.nextLine();
                    System.out.print("Digite o número da conta destino: ");
                    String numDestino = sc.nextLine();
                    System.out.print("Digite o valor para transferir: ");
                    double valorTransf = sc.nextDouble();
                    sc.nextLine();

                    Conta contaOrigem = contaRepository.buscaPorNumero(numOrigem);
                    Conta  contaDestino = contaRepository.buscaPorNumero(numDestino);

                    if (contaOrigem == null || contaDestino == null) {
                        System.out.println();
                        exibirMensagemContaOrigemDestinoNaoEncontrada();
                    } else if (valorTransf <= 0) {
                        System.out.println();
                        exibirMensagemValorInvalidoTransferencia();
                    } else if (contaOrigem.getSaldo() < valorTransf) {
                        System.out.println();
                        exibirMensagemSaldoInsuficiente();
                    } else {
                        contaOrigem.sacar(valorTransf);
                        contaDestino.depositar(valorTransf);
                        System.out.println("Transferência realizada com sucesso.");
                    }
                    break;

            case 5:
                System.out.println();
                System.out.print("Digite o número da conta para consultar saldo: ");
                String numSaldo = sc.nextLine();
                Conta contaSaldo = contaRepository.buscaPorNumero(numSaldo);

                if (contaSaldo == null) {
                    System.out.println();
                    exibirMensagemErroContaNaoEncontrada();
                } else {
                    System.out.println("Saldo atual: R$ " + contaSaldo.getSaldo());
                }
                break;

                case 6:
                    System.out.println();
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println();
                    exibirMensagemOpcaoInvalida();
            }
        } while ( opcao != 6);

        sc.close();
    }

    private static void exibirMensagemErroContaNaoEncontrada() {
        System.out.println("======================================");
        System.out.println("-------> Conta não encontrada <-------");
        System.out.println("======================================");
    }
    private static void exibirMensagemCrieContaPrimeiro() {
        System.out.println("======================================");
        System.out.println("-----> Crie uma conta primeiro <------");
        System.out.println("======================================");
    }
    private static void exibirMensagemValorInvalidoSaque() {
        System.out.println("========================================");
        System.out.println("-----> Valor inválido para saque <------");
        System.out.println("========================================");
    }
    private static void exibirMensagemSaldoInsuficiente() {
        System.out.println("======================================");
        System.out.println("--------> Saldo insuficiente <--------");
        System.out.println("======================================");
    }
    private static void exibirMensagemValorInvalidoDeposito() {
        System.out.println("================================================");
        System.out.println("Valor inválido! O valor deve ser maior que zero.");
        System.out.println("================================================");
    }
    private static void exibirMensagemValorInvalidoTransferencia() {
        System.out.println("==================================");
        System.out.println("Valor inválido para transferência!");
        System.out.println("==================================");
    }
    private static void exibirMensagemContaOrigemDestinoNaoEncontrada() {
        System.out.println("=======================================");
        System.out.println("Conta origem ou destino não encontrada!");
        System.out.println("=======================================");
    }
    private static void exibirMensagemOpcaoInvalida() {
        System.out.println("======================================");
        System.out.println("----------> Opção inválida <----------");
        System.out.println("======================================");
    }
}