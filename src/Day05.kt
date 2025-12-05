import kotlin.math.max
import kotlin.math.min

fun main() {
    fun rangeIncludes(range: Pair<Long, Long>, num: Long): Boolean {
        return num >= range.first && num <= range.second
    }

    fun mergeRanges(range1: Pair<Long, Long>, range2: Pair<Long, Long>): Pair<Boolean, Pair<Long, Long>> {
        if (!rangeIncludes(range1, range2.first) && !rangeIncludes(range2, range1.first)) return false to (-1L to -1L)

        return true to (min(range1.first, range2.first) to max(range1.second, range2.second))
    }

    fun part1(input: List<String>): Int {
        val raw = input.joinToString("\n")
        val ranges = raw.split("\n\n")[0].split("\n").map {
            val nums = it.split("-").map { n -> n.toLong() }
            nums[0] to nums[1]
        }
        val ingredients = raw.split("\n\n")[1].split("\n").map { it.toLong() }

        return ingredients.count { ingredient -> ranges.any { range -> rangeIncludes(range, ingredient) } }
    }

    fun part2(input: List<String>): Long {
        val raw = input.joinToString("\n")
        val ranges = raw.split("\n\n")[0].split("\n").map {
            val nums = it.split("-").map { n -> n.toLong() }
            nums[0] to nums[1]
        }.sortedBy { it.first }

        val mergedRanges = mutableListOf<Pair<Long, Long>>()
        var current = ranges.first()
        for (i in ranges.drop(1)) {
            val mergeResult = mergeRanges(current, i)
            if (!mergeResult.first) {
                mergedRanges.add(current)
                current = i
            } else {
                current = mergeResult.second
            }
        }
        mergedRanges.add(current)

        var count = 0L
        for (entry in mergedRanges) {
            count += entry.second - entry.first + 1
        }

        return count
    }

    val testInput = readInput("day5_test")

    check(part1(testInput) == 3)
    check(part2(testInput) == 14L)

    val input = readInput("day5")
    println(part1(input))
    println(part2(input))
}