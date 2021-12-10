fun main() {
    fun part1(input: List<List<String>>): Int {
        return input.sumOf { it.filter { e -> (e.length in listOf(2, 3, 4, 7)) }.size } - 4 * input.size
    }

    fun part2(input: List<List<String>>): Int =
        input.sumOf { l ->
            val left = l.take(10)
            val right = l.drop(10)
            val pos = MutableList(10) { -1 }
            pos[1] = left.indexOfFirst { it.length == 2 }
            pos[4] = left.indexOfFirst { it.length == 4 }
            pos[7] = left.indexOfFirst { it.length == 3 }
            pos[8] = left.indexOfFirst { it.length == 7 }
            pos[3] = left.indexOfFirst { it.length == 5 && left[pos[1]].all { ch -> ch in it } }
            pos[9] = left.indexOfFirst { it.length == 6 && left[pos[3]].all { ch -> ch in it } }
            pos[6] = left.indexOfFirst { it.length == 6 && !left[pos[7]].all { ch -> ch in it } }
            pos[5] = left.indexOfFirst { it.length == 5 && left[pos[6]].filter { ch -> ch in it }.length == 5 }
            pos[0] = left.indexOfFirst { it.length == 6 && pos.all { i -> i == -1 || left[i] != it } }
            pos[2] = left.indexOfFirst { pos.all { i -> i == -1 || left[i] != it } }
            right.fold(0 as Int) { acc, s -> acc * 10 + pos.indexOfFirst { i -> left[i] == s } }
        }

    val input = readInput("Day08").map { it.split(" | ", " ").map { e -> e.split("").sorted().joinToString("") } }
    println(part1(input))
    println(part2(input))
}