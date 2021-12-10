import kotlin.time.Duration.Companion.seconds

fun main() {
    fun formatInput(input: List<String>): Pair<List<Int>, List<List<List<Int>>>> {
        val order = input[0].split(",").map(String::toInt)
        val boards = input.drop(2).joinToString("\n").split("\n\n")
            .map { s -> s.split("\n").map { l -> l.trim().split(Regex("\\s+")).map(String::toInt) } }
        return Pair(order, boards)
    }

    fun part1(input: List<String>, magic: Int = 1): Int {
        fun pin(board: List<List<Int>>, el: Int): Pair<List<List<Int>>, Boolean> {
            if (board.any { l -> l.all { num -> num == -1 } } ||
                (0..4).any { i -> board.all { l -> l[i] == -1 } }) return Pair(board, false)
            val newBoard = board.map { l -> l.map { e -> if (e == el) -1 else e } }
            return Pair(newBoard, true)
        }
        val (order, boards) = formatInput(input)
        val top = boards.map { board ->
            var board = board
            val pinned = order.takeWhile {
                val (b, result) = pin(board, it)
                board = b
                result
            }
            val sumNonPinned = board.sumOf { l -> l.sumOf { e -> if (e == -1) 0 else e } }
            Triple(pinned.size * magic, pinned.last(), sumNonPinned)
        }.minByOrNull { triple -> triple.first }!!
        return top.second * top.third
    }

    fun part2(input: List<String>): Int = part1(input, -1)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
