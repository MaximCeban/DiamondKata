import java.util.*

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val diamondPrinter: DiamondPrinter = UpperCaseDiamondPrinter()
            val inputParam = args.firstOrNull()
            val char: Char = if (inputParam.isNullOrEmpty()) {
                println("Oh, seems that you didn't pass any param. Please introduce any letter from range 'A' to 'Z")
                val scanner = Scanner(System.`in`)
                scanner.next().first()
            } else {
                inputParam.first()
            }
            println(diamondPrinter.printDiamond(char))
        }
    }
}