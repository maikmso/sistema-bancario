package br.com.miqueias_oliveira.sistemabancario.service;

import br.com.miqueias_oliveira.sistemabancario.model.Conta;

public class ContaService {

    // Número inicial da conta
    private static int contadorNumero= 10000;

    /*
    Gera um número de conta simples incrementando um contador
    e adicionando um dígito verificador.
    Exemplo: 10000 + "-" + 1
    */
    private String gerarNumeroConta() {
        contadorNumero++;
        int digitoVerificador = contadorNumero % 10;
        return contadorNumero + "-" + digitoVerificador;
    }

    // Cria uma nova conta com número automático e saldo zero.
    public Conta criarConta(String titular) {
        String numero = gerarNumeroConta();
        return new Conta(numero, titular);
    }
}