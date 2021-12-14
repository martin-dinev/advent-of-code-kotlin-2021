import kotlin.math.min
import kotlin.time.Duration.Companion.seconds

fun main() {
    fun formatInput2(input: List<String>): Pair<List<Int>, List<List<List<Int>>>> {
        val order = input[0].split(",").map(String::toInt)
        val boards = input.drop(2).joinToString("\n").split("\n\n")
            .map { s -> s.split("\n").map { l -> l.trim().split(Regex("\\s+")).map { i -> order.indexOf(i.toInt()) } } }
        return Pair(order, boards)
    }

    fun part1(input: List<String>, magic: Int = 1): Int {
        val (order, boards) = formatInput2(input)
        val top = boards.map { board ->
            val time = min(board.minOf { it.maxOf { e -> e } }, (0..4).minOf { i -> board.maxOf { l -> l[i] } })
            Triple(time * magic, order[time], board.sumOf { l -> l.sumOf { e -> if (e <= time) 0 else order[e] } })
        }.minByOrNull { triple -> triple.first }!!
        return top.second * top.third
    }

    fun part2(input: List<String>): Int = part1(input, -1)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
