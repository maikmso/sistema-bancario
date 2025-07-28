# 🏦 Sistema Bancário em Java

Este é um projeto simples de sistema bancário desenvolvido em Java, com menu interativo 
no terminal. Ele permite criar uma conta, realizar depósitos, saques e consultar o saldo, 
utilizando boas práticas de organização em camadas (Model, Service, Repository, Util) e uma classe Main para interface principal.

---

## ✨ Funcionalidades

- ✅ Criar conta com nome do titular e número da conta automático.
- 💰 Realizar depósitos com validação de valor.
- 💸 Efetuar saques com verificação de saldo e valor válido.
- 🔄 Transferir valores entre contas com validação de saldo e contas de origem e destino.
- 📋 Listar todas as contas cadastradas no sistema, mostrando informações relevantes.
- 📊 Ver saldo atual da conta.
- ❌ Mensagens claras para erros: conta inexistente, saldo insuficiente e valores inválidos.

---

## 📁 Estrutura do Projeto

```plaintext

src/
└── br/
    └── com/
        └── miqueias_oliveira/
            └── sistemabancario/
                ├── model/          # Contém a classe Conta (entidade)
                │   └── Conta.java
                ├── service/        # Contém a classe ContaService (lógica de negócio)
                │   └── ContaService.java
                ├── repository/     # Camada de acesso a dados (futuramente: ContaRepository.java)
                ├── util/           # Funções auxiliares (ex: Validadores, Formatadores, etc)
                └── Main.java      # Classe principal com menu interativo (interface console)
```