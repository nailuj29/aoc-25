import java.io.File
import java.nio.file.Files
import java.nio.file.Path

const val DAY = 2

fun main() {
    var template = File("src/template.kt").readText()

    template = template.replace("{day}", DAY.toString())

    if (Files.exists(Path.of("src/Day${DAY.toString().padStart(2, '0')}.kt"))) {
        println("Source code file for today already exists, exiting.")
        return
    }

    File("src/Day${DAY.toString().padStart(2, '0')}.kt").writeText(template)
    File("src/day${DAY}.txt").writeText("")
    File("src/day${DAY}_test.txt").writeText("")

    println("Files created successfully")
}