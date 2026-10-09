
fun validarBioInfantil(bio: String?) {
    val tamanho = bio?.length ?: 0

    if (tamanho <= 50) {
        println("Bio aceita")
    } else {
        println("Bio muito longa")
    }
}

fun main() {
    validarBioInfantil("Olá, meu nome é Alex!")
    validarBioInfantil(null)
    validarBioInfantil("A".repeat(51))
}