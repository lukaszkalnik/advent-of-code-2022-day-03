import okio.FileSystem
import okio.Path.Companion.toPath

var prioritySum = 0

fun main(args: Array<String>) {
    val path = args[0].toPath()

    FileSystem.SYSTEM.read(path) {
        while (true) {
            val line1 = readUtf8Line() ?: break
            if (line1.isBlank()) break
            val line2 = readUtf8Line() ?: break
            val line3 = readUtf8Line() ?: break

            val rucksack1 = line1.toSet()
            val rucksack2 = line2.toSet()
            val rucksack3 = line3.toSet()

            val sharedItem = rucksack1.intersect(rucksack2).intersect(rucksack3).first()
            println(line1)
            println(line2)
            println(line3)
            println(sharedItem)
            prioritySum += sharedItem.priority
        }
        println("Priority sum: $prioritySum")
    }
}

private const val uppercaseALetterCode = 'A'.code
private const val uppercaseALetterPriority = 27

private const val lowercaseALetterCode = 'a'.code
private const val lowercaseALetterPriority = 1

val Char.priority: Int
    get() = when (this) {
        in 'a'..'z' -> code - lowercaseALetterCode + lowercaseALetterPriority
        in 'A'..'Z' -> code - uppercaseALetterCode + uppercaseALetterPriority
        else -> throw IllegalArgumentException("char can only be in A..Za..z but was $this")
    }