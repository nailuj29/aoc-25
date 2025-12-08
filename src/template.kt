fun main() {
    fun part1(input: List<String>): Long {
        return -1L
    }

//    fun part2(input: List<String>): Long {
//
//    }

    val testInput = readInput("day{day}_test")

    check(part1(testInput) == 0L)
//    check(part2(testInput) == 0L)

    val input = readInput("day{day}")
    println(part1(input))
//    println(part2(input))
}