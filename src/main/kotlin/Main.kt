import okio.FileSystem
import okio.Path.Companion.toPath

var prioritySum = 0

fun main(args: Array<String>) {
    val path = args[0].toPath()

    FileSystem.SYSTEM.read(path) {
        while (true) {
            val line = readUtf8Line() ?: break
            if (line.isBlank()) break

            val halfLength = line.length / 2
            val compartment1String = line.substring(0, halfLength)
            val compartment2String = line.substring(halfLength)

            val compartment1 = compartment1String.toSet()
            val compartment2 = compartment2String.toSet()

            var sharedItem: Char = '?'

            run breaking@{
                compartment1.forEach { char1 ->
                    compartment2.find { it == char1 }?.let {
                        sharedItem = it
                        return@breaking
                    }
                }
            }

            println(line)
            println("$compartment1String, $compartment2String")
            println("$compartment1, $compartment2")
            println(sharedItem)
            println(sharedItem.priority)
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