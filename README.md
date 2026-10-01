Caixa Eletrônico (ATM) à Prova de Falhas

Programa interativo de terminal feito em Kotlin.

Estudo de caso escolhido

Caixa Eletrônico (ATM) à Prova de Falhas: o usuário se autentica com um PIN (3 tentativas) e, depois, acessa um menu para consultar saldo, sacar, depositar e ver o extrato.

Como funciona
Autenticação: laço while com no máximo 3 tentativas. Após 3 erros, o cartão é bloqueado.
Saque: só é permitido se saldo >= valor && valor % 10 == 0.
Null Safety: a leitura usa readlnOrNull()?.trim() ?: "" e toIntOrNull(), então entradas vazias ou com letras não quebram o programa.
Funções: a lógica está separada em funções (autenticar, sacar, depositar, exibirExtrato, etc.).
Estruturas: if/else, when e laços while/for.

Dados iniciais: saldo de R$ 1000 e PIN 1234.