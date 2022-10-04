const val UNDERSCORE = '_'
const val A = 'A'
const val NEW_LINE = "\n"
val ALLOWED_CHARS = ('A'..'Z')

class UpperCaseDiamondPrinter : DiamondPrinter {
    override fun printDiamond(char: Char): String {
        if (char !in ALLOWED_CHARS) {
            throw java.lang.IllegalArgumentException("Only 'A' to 'Z' chars are allowed")
        }
        val maxOffset = getOffset(char)
        return (A..char).map {
            mirrorSemiRow(createSemiRow(it, maxOffset))
        }.mirror().joinToString(NEW_LINE)
    }

    private fun createSemiRow(char: Char, maxOffset: Int): String {
        val currentOffset = getOffset(char)
        val leftPart = StringBuilder()
        if (maxOffset == 0) {
            leftPart.append(char)
        } else {
            (maxOffset until currentOffset).forEach { _ ->
                leftPart.append(UNDERSCORE)
            }
            leftPart.append(char)

            (currentOffset until 0).forEach { _ ->
                leftPart.append(UNDERSCORE)
            }
        }
        return leftPart.toString()
    }

    private fun mirrorSemiRow(leftSemiRow: String): String {
        return leftSemiRow.plus(leftSemiRow.substring(0..leftSemiRow.length - 2).reversed())

    }

    private fun getOffset(char: Char) = A - char
    private fun <T> List<T>.mirror(): List<T> {
        return this + this.slice(lastIndex - 1 downTo 0)
    }
}