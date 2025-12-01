fun main() {
    fun part1(input: List<String>): Int {
        return -1
    }

//    fun part2(input: List<String>): Int {
//
//    }

    val testInput = readInput("day{day}_test")

    check(part1(testInput) == 0)
//    check(part2(testInput) == 0)

    val input = readInput("day{day}")
    println(part1(input))
//    println(part2(input))
}