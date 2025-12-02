fun main() {
    fun part1(input: List<String>): Int {
        return -1
    }

//    fun part2(input: List<String>): Int {
//
//    }

    val testInput = readInput("day2_test")

    check(part1(testInput) == 0)
//    check(part2(testInput) == 0)

    val input = readInput("day2")
    println(part1(input))
//    println(part2(input))
}