import okio.FileSystem
import okio.Path.Companion.toPath

fun main(args: Array<String>) {
    val path = args[0].toPath()

    FileSystem.SYSTEM.read(path) {
        while(true) {
            val line = readUtf8Line() ?: return
            if (line.isBlank()) return

        }
    }
}