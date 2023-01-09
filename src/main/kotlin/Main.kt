import okio.FileSystem
import okio.Path.Companion.toPath

var rucksacks: List<Pair<Compartment, Compartment>> = mutableListOf()

fun main(args: Array<String>) {
    val path = args[0].toPath()

    FileSystem.SYSTEM.read(path) {
        while (true) {
            val line = readUtf8Line() ?: return
            if (line.isBlank()) return


        }
    }
}

data class Compartment(
    val items: List<Item>,
)

private const val uppercaseALetterCode = 'A'.code
private const val uppercaseALetterPriority = 27

private const val lowercaseALetterCode = 'a'.code
private const val lowercaseALetterPriority = 1

@JvmInline
value class Item(val letter: Char) {

    val priority: Int
        get() = when (letter) {
            in 'a'..'z' -> letter.code - lowercaseALetterCode + lowercaseALetterPriority
            in 'A'..'Z' -> letter.code - uppercaseALetterCode + uppercaseALetterPriority
            else -> throw IllegalArgumentException("char can only be in A..Za..z but was $letter")
        }
}
