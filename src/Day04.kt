fun main() {
    fun check(grid: List<List<Boolean>>, coords: Pair<Int, Int>): Boolean {
        try {
            return grid[coords.first][coords.second]
        } catch (e: Exception) {
            return false
        }
    }

    fun countNeighbors(grid: List<List<Boolean>>, coords: Pair<Int, Int>): Int {
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
        val grid = input.map { it.map { c -> c == '@' } }

        var count = 0

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] && countNeighbors(grid, i to j) < 4) count++
            }
        }

        return count
    }

    fun part2(input: List<String>): Int {
        val grid = input.map { it.map { c -> c == '@'}.toMutableList() }.toMutableList()
        var count = 0

        var didRemove = false
        do {
            didRemove = false

            for (i in grid.indices) {
                for (j in grid[i].indices) {
                    if (grid[i][j] && countNeighbors(grid, i to j) < 4) {
                        count++
                        didRemove = true
                        grid[i][j] = false
                    }
                }
            }
        } while (didRemove)

        return count
    }

    val testInput = readInput("day4_test")

    check(part1(testInput) == 13)
    check(part2(testInput) == 43)

    val input = readInput("day4")
    println(part1(input))
    println(part2(input))
}