const val PIN_CORRETO = "1234"
const val MAX_TENTATIVAS = 3

var saldo: Int = 1000
val extrato = mutableListOf<String>()

// ---------- Entrada de dados (com Null Safety) ----------

// readlnOrNull() pode devolver null (ex.: Ctrl+D). Safe call (?.) + Elvis (?:) tratam isso.
fun lerTexto(mensagem: String): String {
    print(mensagem)
    return readlnOrNull()?.trim() ?: ""
}

// toIntOrNull() devolve null se o usuário digitar letras ou nada.
fun lerInteiro(mensagem: String): Int? = lerTexto(mensagem).toIntOrNull()

// ---------- Autenticação ----------

fun autenticar(): Boolean {
    var tentativa = 1
    while (tentativa <= MAX_TENTATIVAS) {
        val pin = lerTexto("Digite seu PIN (tentativa $tentativa/$MAX_TENTATIVAS): ")
        val restantes = MAX_TENTATIVAS - tentativa

        when {
            pin.isEmpty() -> println("PIN vazio! Você precisa digitar algo. Restam $restantes tentativa(s).")
            pin == PIN_CORRETO -> return true
            else -> println("PIN incorreto. Restam $restantes tentativa(s).")
        }
        tentativa++
    }
    return false
}

// ---------- Operações ----------

fun exibirMenu() {
    println()
    println("===== CAIXA ELETRÔNICO =====")
    println("1 - Consultar saldo")
    println("2 - Sacar")
    println("3 - Depositar")
    println("4 - Extrato")
    println("0 - Sair")
}

fun consultarSaldo() {
    println("Saldo atual: R$ $saldo")
}

fun calcularNotas(valor: Int): String {
    var resto = valor
    val notas = listOf(100, 50, 20, 10)
    val partes = mutableListOf<String>()
    for (nota in notas) {
        val quantidade = resto / nota
        if (quantidade > 0) {
            partes.add("$quantidade x R$ $nota")
            resto %= nota
        }
    }
    return partes.joinToString(", ")
}

fun sacar() {
    val valor = lerInteiro("Valor do saque: R$ ")

    if (valor == null) {
        println("Valor inválido! Digite apenas números inteiros.")
        return
    }
    if (valor <= 0) {
        println("O valor precisa ser maior que zero.")
        return
    }

    if (saldo >= valor && valor % 10 == 0) {
        saldo -= valor
        extrato.add("Saque:    - R$ $valor")
        println("Saque realizado! Notas entregues: ${calcularNotas(valor)}")
        println("Novo saldo: R$ $saldo")
    } else if (valor > saldo) {
        println("Saldo insuficiente. Seu saldo é R$ $saldo.")
    } else {
        println("Só é possível sacar valores múltiplos de 10.")
    }
}

fun depositar() {
    val valor = lerInteiro("Valor do depósito: R$ ")

    if (valor == null || valor <= 0) {
        println("Valor inválido! Digite um número inteiro maior que zero.")
        return
    }

    saldo += valor
    extrato.add("Depósito: + R$ $valor")
    println("Depósito realizado! Novo saldo: R$ $saldo")
}

fun exibirExtrato() {
    println("----- EXTRATO -----")
    if (extrato.isEmpty()) {
        println("Nenhuma movimentação nesta sessão.")
    } else {
        for (linha in extrato) {
            println(linha)
        }
    }
    println("Saldo atual: R$ $saldo")
}

// ---------- Programa principal ----------

fun main() {
    println("Bem-vindo ao Caixa Eletrônico!")

    if (!autenticar()) {
        println("Número máximo de tentativas excedido. Cartão bloqueado.")
        return
    }

    println("Autenticação realizada com sucesso!")

    var executando = true
    while (executando) {
        exibirMenu()
        when (lerTexto("Escolha uma opção: ")) {
            "1" -> consultarSaldo()
            "2" -> sacar()
            "3" -> depositar()
            "4" -> exibirExtrato()
            "0" -> {
                println("Obrigado por usar o Caixa Eletrônico. Até logo!")
                executando = false
            }
            else -> println("Opção inválida! Escolha entre 0 e 4.")
        }
    }
}