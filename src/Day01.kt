import java.lang.Integer.parseInt
import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        var dial = 50
        var zeroCount = 0

        for (line in input) {
            val dir = line[0] == 'L'
            val count = parseInt(line.substring(1))

            dial += count * if (dir) -1 else 1

            while (dial < 0) {
                dial += 100
            }

            dial %= 100

            if (dial == 0) zeroCount++
        }

        return zeroCount
    }

    fun part2(input: List<String>): Int {
        var dial = 50
        var zeroCount = 0

        for (line in input) {
            val prevDial = dial
            val dir = line[0] == 'L'
            val count = parseInt(line.substring(1))

            for (i in 1..count) {
                dial += if (dir) -1 else 1
                dial = (100 + dial) % 100

                if (dial == 0) {
                    println(line)
                    zeroCount++
                }
            }

        }

        return zeroCount
    }

    val testInput = readInput("day1_test")

    check(part1(testInput) == 3)
    check(part2(testInput) == 6)

    val input = readInput("day1")
    println(part1(input))
    println(part2(input))
}