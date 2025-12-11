data class Node(val name: String, val children: List<String>)

fun main() {

    val numPathsToCache = mutableMapOf<Pair<String, String>, Long>()
    fun numPathsTo(node: Node, dest: String, allNodes: Map<String, Node>): Long {
        if (numPathsToCache.containsKey(node.name to dest)) return numPathsToCache[node.name to dest]!!
        return if (node.children.contains(dest)) 1
        else if (node.children.contains("out")) 0
        else {
            val result = node.children.sumOf { child -> numPathsTo(allNodes[child]!!, dest, allNodes) }
            numPathsToCache[node.name to dest] = result
            result
        }
    }

    fun numPaths(node: Node, allNodes: Map<String, Node>): Long {
        return numPathsTo(node, "out", allNodes)
    }

    fun part1(input: List<String>): Long {
        val nodes = input.associate {
            val splitResult = it.split(": ")
            splitResult[0] to Node(splitResult[0], splitResult[1].split(" "))
        }

        return numPaths(nodes["you"]!!, nodes)
    }

    fun part2(input: List<String>): Long {
        numPathsToCache.clear()
        val nodes = input.associate {
            val splitResult = it.split(": ")
            splitResult[0] to Node(splitResult[0], splitResult[1].split(" "))
        }

        val svrDac = numPathsTo(nodes["svr"]!!, "dac", nodes)
        val dacFft = numPathsTo(nodes["dac"]!!, "fft", nodes)
        val fftOut = numPathsTo(nodes["fft"]!!, "out", nodes)

        val svrFft = numPathsTo(nodes["svr"]!!, "fft", nodes)
        val fftDac = numPathsTo(nodes["fft"]!!, "dac", nodes)
        val dacOut = numPathsTo(nodes["dac"]!!, "out", nodes)

        return svrDac * dacFft * fftOut + svrFft * fftDac * dacOut
    }

    val testInput = readInput("day11_test")
    val testInput2 = readInput("day11_test2")

    check(part1(testInput) == 5L)
    check(part2(testInput2) == 2L)

    val input = readInput("day11")
    println(part1(input))
    println(part2(input))
}