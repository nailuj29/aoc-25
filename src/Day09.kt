import java.awt.Polygon
import java.awt.geom.Area
import java.awt.geom.Rectangle2D
import kotlin.math.abs
import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Long {
        val redTiles = input.map { it.split(",").map { it.toLong() } }

        var maxArea = 0L
        for (i in redTiles.indices) {
            for (j in i + 1 until redTiles.size) {
                val tile_i = redTiles[i]
                val tile_j = redTiles[j]

                val area = abs(tile_i[0] - tile_j[0] + 1) * abs(tile_i[1] - tile_j[1] + 1)
                if (area > maxArea) {
                    maxArea = area
                }
            }
        }

        return maxArea
    }

    fun part2(input: List<String>): Long {
        val redTiles = input.map { it.split(",").map { it.toLong() } }
        val polygon = Polygon(redTiles.map { it[0].toInt() }.toIntArray(), redTiles.map { it[1].toInt() }.toIntArray(), redTiles.size)
        val validArea = Area(polygon)

        var maxArea = 0L
        for (i in redTiles.indices) {
            for (j in i + 1 until redTiles.size) {
                val tile_i = redTiles[i]
                val tile_j = redTiles[j]

                val area = (abs(tile_i[0] - tile_j[0]) + 1) * (abs(tile_i[1] - tile_j[1]) + 1)
                val x = min(tile_i[0], tile_j[0]).toDouble()
                val y = min(tile_i[1], tile_j[1]).toDouble()
                val w = abs(tile_i[0] - tile_j[0]).toDouble()
                val h = abs(tile_i[1] - tile_j[1]).toDouble()
                if (area > maxArea && validArea.contains(Rectangle2D.Double(x,
                        y, w, h
                    ))) {
                    maxArea = area
                }
            }
        }

        return maxArea
    }

    val testInput = readInput("day9_test")

    check(part1(testInput) == 50L)
    check(part2(testInput) == 24L)

    val input = readInput("day9")
    println(part1(input))
    println(part2(input))
}
