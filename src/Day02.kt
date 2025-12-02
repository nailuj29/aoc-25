fun main() {
    fun checkRepeats(string: String, num: Int): Boolean {
        val chunks = string.chunked(num)

        return chunks.toSet().size == 1
    }

    fun part1(input: List<String>): Long {
        val line = input[0]
        val ids = line.split(",").flatMap {
            val (fst, snd) = it.split("-")
            fst.toLong()..snd.toLong()
        }

        var total = 0L
        for (id in ids) {
            val str = id.toString()
            val firstHalf = str.take(str.length / 2)
            val secondHalf = str.drop(str.length / 2)

            if (firstHalf == secondHalf) {
                total += id
            }
        }

        return total
    }

    fun part2(input: List<String>): Long {
        val line = input[0]
        val ids = line.split(",").flatMap {
            val (fst, snd) = it.split("-")
            fst.toLong()..snd.toLong()
        }

        var total = 0L
        for (id in ids) {
            val str = id.toString()

            for (i in 1..str.length/2) {
                if (str.length % i == 0) {
                    if (checkRepeats(str, i)) {
                        total += id
                        break
                    }
                }
            }
        }

        return total
    }

    val testInput = readInput("day2_test")

    check(part1(testInput) == 1227775554L)
    check(part2(testInput) == 4174379265L)

    val input = readInput("day2")
    println(part1(input))
    println(part2(input))
}