fun main() {
    fun part1(input: List<Pair<String, Int>>): Int {
        val (forward, depth) = input.fold(Pair(0, 0)) { acc, pair ->
            when (pair.first) {
                "up" -> Pair(acc.first, acc.second - pair.second)
                "down" -> Pair(acc.first, acc.second + pair.second)
                else -> Pair(acc.first + pair.second, acc.second)
            }
        }
        return forward * depth
    }

    fun part2(input: List<Pair<String, Int>>): Int {
        val (forward, depth) = input.fold(Triple(0, 0, 0)) { acc, pair ->
            when (pair.first) {
                "up" -> Triple(acc.first, acc.second, acc.third - pair.second)
                "down" -> Triple(acc.first, acc.second, acc.third + pair.second)
                else -> Triple(acc.first + pair.second, acc.second + acc.third * pair.second, acc.third)
            }
        }
        return forward * depth
    }

    val input = readInput("Day02").map {
        val e = it.split(" ")
        Pair(e[0], e[1].toInt())
    }
    println(part1(input))
    println(part2(input))
}
