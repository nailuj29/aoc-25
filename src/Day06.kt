fun main() {
    fun part1(input: List<String>): Long {
        val ops = input.last().split("\\s+".toRegex())
        val numLines = input.dropLast(1).map {
            it.trim().split("\\s+".toRegex()).map { num -> num.toLong() }
        }

        val current = numLines.first().toMutableList()
        for (numLine in numLines.drop(1)) {
            for (i in numLine.indices) {
                if (ops[i] == "+") {
                    current[i] += numLine[i]
                } else {
                    current[i] *= numLine[i]
                }
            }
        }

        return current.sum()
    }

    fun part2(input: List<String>): Long {
        val ops = input.last().split("\\s+".toRegex())
        val columnEnds = (0 until input[0].lastIndex).filter { colmn ->
            input.all { line ->
                line[colmn] == ' '
            }
        }.toMutableList()
        columnEnds.add(0, 0)

        val columns = mutableListOf<List<String>>()
        for (pair in columnEnds.windowed(2)) {
            columns.add(input.dropLast(1).map {
                it.substring(pair.first(), pair.last())
            })
        }
        columns.add(input.dropLast(1).map {
            it.substring(columnEnds.last())
        })

        val numbers = mutableListOf<List<Long>>()
        for (column in columns) {
            val currentList = mutableListOf<Long>()
            for (i in 0 until column.maxOf { it.length }) {
                val sb = StringBuilder()
                for (num in column) {
                    try {
                        sb.append(num[i])
                    } catch (e: Exception) {}
                }
                if (sb.toString().trim().isNotEmpty()) { // Column delims included
                    currentList.add(sb.toString().trim().toLong())
                }
            }
            numbers.add(currentList)
        }

        val current = mutableListOf<Long>()
        for (numLine in numbers.withIndex()) {
            current.add(numLine.value.reduce { a, b -> if (ops[numLine.index] == "+") a + b else a * b })
        }

        return current.sum()
    }

    val testInput = readInput("day6_test")

    check(part1(testInput) == 4277556L)
    check(part2(testInput) == 3263827L)

    val input = readInput("day6")
    println(part1(input))
    println(part2(input))
}