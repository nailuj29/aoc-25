fun main() {
    fun check(grid: List<String>, coords: Pair<Int, Int>): Boolean {
        try {
            return grid[coords.first][coords.second] == '@'
        } catch (e: Exception) {
            return false
        }
    }

    fun countNeighbors(grid: List<String>, coords: Pair<Int, Int>): Int {
        var neighbors = 0

        for (i in -1..1) {
            for (j in -1..1) {
                if (i to j == 0 to 0) continue
                if (check(grid, coords.first + i to coords.second + j)) neighbors++
            }
        }

        return neighbors
    }

    fun part1(input: List<String>): Int {
        var count = 0

        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] == '@' && countNeighbors(input, i to j) < 4) count++
            }
        }

        return count
    }

//    fun part2(input: List<String>): Int {
//
//    }

    val testInput = readInput("day4_test")

    check(part1(testInput) == 13)
//    check(part2(testInput) == 0)

    val input = readInput("day4")
    println(part1(input))
//    println(part2(input))
}