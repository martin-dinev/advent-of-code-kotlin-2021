fun main() {
    fun part1(input: List<List<Int>>, partTwo: Boolean = false): Int {
        val mp = List(1000) { MutableList(1000) { 0 } }
        input.forEach { e ->
            if (e[0] == e[2]) for (i in range(e[1], e[3])) mp[e[0]][i]++
            else if (e[1] == e[3]) for (i in range(e[0], e[2])) mp[i][e[1]]++
            else if (partTwo) for ((i, j) in range(e[0], e[2]) zip range(e[1], e[3])) mp[i][j]++
        }
        return mp.sumOf { l -> l.count { e -> e > 1 } }
    }

    fun part2(input: List<List<Int>>): Int = part1(input, true)

    val input = readInput("Day05").map { l -> l.split(" -> ", ",").map(String::toInt) }
    println(part1(input))
    println(part2(input))
}
