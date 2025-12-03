fun main() {
    fun maxJoltage(bank: String): Int {
        var maxJoltage = 0
        for (i in 0 until bank.length) {
            for (j in i+1 until bank.length) {
                val joltage = (bank[i].toString() + bank[j].toString()).toInt()
                if (joltage > maxJoltage) {
                    maxJoltage = joltage
                }
            }
        }

        return maxJoltage
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { maxJoltage(it) }
    }

    fun part2(input: List<String>): Long {
        var total = 0L

        for (line in input) {
            var currentFirst = -1
            val max = StringBuilder()
            for (i in 1 .. 12) {
                val digit = line.drop(currentFirst + 1).dropLast(12 - i).max()
                currentFirst = line.indexOf(digit, currentFirst + 1)

                max.append(digit)
            }

            println(max.toString())
            total += max.toString().toLong()
        }

        return total
    }

    val testInput = readInput("day3_test")

    check(part1(testInput) == 357)
    check(part2(testInput) == 3121910778619L)

    val input = readInput("day3")
    println(part1(input))
    println(part2(input))
}