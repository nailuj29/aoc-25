data class Manual(val buttonMap: List<List<Int>>, val desiredJoltages: List<Int>)
data class MachineState(val joltages: List<Int>, val buttonMap: List<List<Int>>)

fun main() {
    val leastPressesCache = mutableMapOf<MachineState, Long>()
    fun leastPresses(
        presses: List<Int>,
        map: List<List<Int>>,
        joltages: List<Int>,
        desiredJoltages: List<Int>,
        max: Long
    ): Long {
        val machineState = MachineState(joltages, map)

        if (joltages.zip(desiredJoltages).any { (a, b) -> a > b }) return Long.MAX_VALUE
        if (desiredJoltages == joltages) {
            return presses.size.toLong()
        }
        if (presses.size > max) return Long.MAX_VALUE

        if (leastPressesCache.containsKey(machineState)) return leastPressesCache[machineState]!!
        var min = Long.MAX_VALUE
        for (choice in map.sortedBy { -it.size }.withIndex()) {
            val newJoltages = joltages.toMutableList()
            for (toggle in choice.value) {
                newJoltages[toggle]++
            }

            val toCheck = leastPresses(
                presses + choice.index,
                map,
                newJoltages,
                desiredJoltages,
                min
            )

            if (toCheck == Long.MAX_VALUE) continue

            if (toCheck < min) {
                min = toCheck
            }

        }

        leastPressesCache[machineState] = min
        return min
    }

    fun <T> subsets(set: Set<T>, num: Int): Set<Set<T>> {
        if (num < 1) return emptySet()
        if (num == 1) return set.map { setOf(it) }.toSet()

        val subsetsNMinus1 = subsets(set, num - 1)
        return set.flatMap { item ->
            val subsetsWithoutItem = subsetsNMinus1.filter { item !in it }
            subsetsWithoutItem.map { it + item }
        }.toSet()
    }

    fun part1(input: List<String>): Long {
        leastPressesCache.clear()
        val ends = input.map { line -> line.split(" ").first().drop(1).dropLast(1).map { c -> c == '#' } }
        val buttonMaps = input.map { line ->
            line.split(" ").drop(1).dropLast(1).map { map ->
                map.drop(1).dropLast(1).split(",").map { idx -> idx.toInt() }
            }
        }
            .mapIndexed { i, maps ->
                maps.map { map ->
                    List(ends[i].size) { false }.mapIndexed { j, _ ->
                        j in map
                    }
                }.toSet()
            } // Order doesn't matter, set makes more sense with some of the operations we're gonna do
        // Can ignore joltages for now
        val manuals = ends.zip(buttonMaps)

        var count = 0L
        outer@ for (manual in manuals) {
            for (i in 1 until manual.second.size) {
                val toCheck = subsets(manual.second, i)

                for (possibility in toCheck) {
                    val current = MutableList(manual.first.size) { false }
                    for (button in possibility) {
                        for (j in button.indices) {
                            current[j] = current[j] xor button[j]
                        }
                    }

                    if (current == manual.first) {
                        count += i
                        continue@outer
                    }
                }
            }
        }

        return count
    }

    fun part2(input: List<String>): Long {
        val buttonMaps = input.map { line ->
            line.split(" ").drop(1).dropLast(1).map { map ->
                map.drop(1).dropLast(1).split(",").map { idx -> idx.toInt() }
            }
        }
        val joltages = input.map { line ->
            line.split(" ").last().drop(1).dropLast(1)
                .split(",").map { idx -> idx.toInt() }

        }
        val manuals = buttonMaps.zip(joltages)

        var count = 0L

        for (manual in manuals) {
            print("#")
            leastPressesCache.clear()
            val temp = leastPresses(
                listOf(),
                manual.first,
                List(manual.second.size) { 0 },
                manual.second,
                Long.MAX_VALUE
            )

            count += temp
        }

        println("")

        return count
    }

    val testInput = readInput("day10_test")

    check(part1(testInput) == 7L)
    check(part2(testInput) == 33L)

    val input = readInput("day10")
    println(part1(input))
    println(part2(input))
}