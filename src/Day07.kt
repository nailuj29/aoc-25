fun main() {
    fun part1(input: List<String>): Int {
        val positions = mutableSetOf(input[0].indexOf("S"))

        var splits = 0
        for (row in input) {
            for (position in positions.toList()) {
                if (row[position] == '^') {
                    splits++
                    positions.remove(position)
                    if (position != input[0].length - 1) {
                        positions.add(position + 1)
                    }

                    if (position != 0) {
                        positions.add(position - 1)
                    }
                }
            }
        }

        return splits
    }

    val cache = mutableMapOf<Pair<Int, Int>, Long>()
    fun numTimelines(rowsLeft: List<String>, currentPos: Int): Long {
        if (cache.containsKey(rowsLeft.size to currentPos)) return cache[rowsLeft.size to currentPos]!!
        if (rowsLeft.isEmpty()) return 1

        val point = rowsLeft[0][currentPos]
        if (point == '.') {
            val retVal = numTimelines(rowsLeft.drop(1), currentPos)
            cache[rowsLeft.size to currentPos] = retVal
            return retVal
        }

        if (point == '^') {
            var retVal = 0L
            if (currentPos != rowsLeft[0].length - 1) {
                retVal += numTimelines(rowsLeft, currentPos + 1)
            }

            if (currentPos != 0) {
                retVal += numTimelines(rowsLeft, currentPos - 1)
            }

            cache[rowsLeft.size to currentPos] = retVal
            return retVal
        }

        throw Exception("Unreachable")
    }

    fun part2(input: List<String>): Long {
        cache.clear()
        val position = input[0].indexOf("S")
        return numTimelines(input.drop(1), position)
    }

    val testInput = readInput("day7_test")

    check(part1(testInput) == 21)
    check(part2(testInput) == 40L)

    val input = readInput("day7")
    println(part1(input))
    println(part2(input))
}