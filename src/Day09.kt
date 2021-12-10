import kotlin.math.abs

fun main() {
    fun ng(i: Int, j: Int, height: Int, width: Int) =
        (-1..1).flatMap { a -> (-1..1).map { b -> Pair(i + a, j + b) } }
            .filter { (f, s) -> f >= 0 && s >= 0 && f < height && s < width && abs(f - i) + abs(s - j) == 1 }

    fun lowPoints(input: List<String>): List<Pair<Int, Int>> =
        input.flatMapIndexed { i, l ->
            l.withIndex().filter { (j, v) ->
                ng(i, j, input.size, input[0].length).none { (f, s) -> input[f][s] <= v }
            }.map { (j, _) -> Pair(i, j) }
        }

    fun part1(input: List<String>): Int =
        lowPoints(input).sumOf { (i, j) -> input[i][j].code - 48 + 1 }


    fun part2(input: List<String>): Int =
        lowPoints(input).map { (fi, fj) ->
            val v = mutableSetOf(Pair(fi, fj))
            val q = ArrayDeque(listOf(Pair(fi, fj)))
            while (q.isNotEmpty()) {
                val (i, j) = q.removeFirst()
                ng(i, j, input.size, input[0].length)
                    .filter { (a, b) -> input[a][b] >= input[i][j] && Pair(a, b) !in v && input[a][b] != '9' }
                    .forEach { v.add(it);q.add(it) }
            }
            v.size
        }.sorted().reversed().take(3).reduce { a, b -> a * b }

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}