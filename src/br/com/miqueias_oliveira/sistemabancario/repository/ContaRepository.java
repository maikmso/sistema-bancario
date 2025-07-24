package br.com.miqueias_oliveira.sistemabancario.repository;

import br.com.miqueias_oliveira.sistemabancario.model.Conta;

import java.util.ArrayList;
import java.util.List;

public class ContaRepository {

    private List<Conta> contas = new ArrayList<>();

    // Salva uma nova conta na lista
    public void salvar(Conta conta) {
        contas.add(conta);
    }

    // Busca uma conta pelo número
    public Conta buscaPorNumero(String numero) {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numero)) {
                return conta;
            }
        }
        return null; // Conta não foi encontrada
    }

    // Retorna todas as contas cadastradas (opcional)
    public List<Conta> listarTodas() {
        return contas;
    }
}