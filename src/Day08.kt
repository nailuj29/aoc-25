import kotlin.math.pow
import kotlin.math.sqrt

data class Point3(val x: Int, val y: Int, val z: Int)

fun main() {
    fun part1(input: List<String>): Int {
        val n = if (input.size == 1000) 1000 else 10
        val boxes = input.map { it.split(",").map { n -> n.toInt() } }.map { Point3(it[0], it[1], it[2]) }

        val circuits = boxes.map { mutableSetOf(it) }.toMutableList()
        val foundPairs = mutableSetOf<Pair<Point3, Point3>>()
        for (i in 0 until n) {
            var shortest = Double.MAX_VALUE
            var shortestPair = Point3(-1, -1, -1) to Point3(-1, -1, -1)

            for (j in boxes.indices) {
                for (k in j+1 until boxes.size) {
                    val p1 = boxes[j]
                    val p2 = boxes[k]

                    val dist = sqrt((p1.x - p2.x).toDouble().pow(2) + (p1.y - p2.y).toDouble().pow(2) + (p1.z - p2.z).toDouble().pow(2))

                    if (dist < shortest && (p1 to p2) !in foundPairs) {
                        shortest = dist
                        shortestPair = p1 to p2
                    }
                }
            }

            foundPairs.add(shortestPair)

            val circuitA = circuits.first { shortestPair.first in it }
            val circuitB = circuits.first { shortestPair.second in it }

            if (circuitA == circuitB) continue

            circuits.remove(circuitB)
            circuitA.addAll(circuitB)
        }

        return circuits.map { it.size }.sortedDescending().take(3).reduce { a, b -> a * b }
    }

    fun part2(input: List<String>): Long {
        val boxes = input.map { it.split(",").map { n -> n.toInt() } }.map { Point3(it[0], it[1], it[2]) }

        val circuits = boxes.map { mutableSetOf(it) }.toMutableList()
        val pairLengths = mutableMapOf<Pair<Point3, Point3>, Double>()
        var lastPairAdded = Point3(-1, -1, -1) to Point3(-1, -1, -1)

        for (j in boxes.indices) {
            for (k in j+1 until boxes.size) {
                val p1 = boxes[j]
                val p2 = boxes[k]

                val dist = sqrt((p1.x - p2.x).toDouble().pow(2) + (p1.y - p2.y).toDouble().pow(2) + (p1.z - p2.z).toDouble().pow(2))

                pairLengths[p1 to p2] = dist
            }
        }

        while (circuits.size > 1) {
            val shortestPair = pairLengths.minBy { it.value }.key
            pairLengths.remove(shortestPair)

            val circuitA = circuits.first { shortestPair.first in it }
            val circuitB = circuits.first { shortestPair.second in it }

            circuits.remove(circuitB)
            circuits.remove(circuitA)

            circuits.add((circuitA union circuitB).toMutableSet())

            lastPairAdded = shortestPair
        }

        return lastPairAdded.first.x.toLong() * lastPairAdded.second.x
    }

    val testInput = readInput("day8_test")

    check(part1(testInput) == 40)
    check(part2(testInput) == 25272L)

    val input = readInput("day8")
    println(part1(input))
    println(part2(input))
}