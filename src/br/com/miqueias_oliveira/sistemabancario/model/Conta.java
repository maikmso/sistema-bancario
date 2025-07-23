package br.com.miqueias_oliveira.sistemabancario.model;

public class Conta {

    private String numero;
    private String titular;
    private double saldo;

    public Conta(String numero, String titular) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0.0;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    // Deposita um valor na conta, aumentando o saldo inicial
    public boolean depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            return true;
        }
        return false;
    }

    // Tenta sacar um valor da conta se houver saldo suficiente.
    public boolean sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Conta: " +
                numero +
                ", Titular: " +
                titular +
                ", Saldo: R$ " + saldo;
    }
}