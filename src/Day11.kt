import kotlin.math.abs

fun main() {
    fun ng(i: Int, j: Int, height: Int, width: Int) =
        (-1..1).flatMap { a -> (-1..1).map { b -> Pair(i + a, j + b) } }
            .filter { (f, s) -> f >= 0 && s >= 0 && f < height && s < width && abs(f - i) + abs(s - j) > 0 }

    fun flash(state: List<MutableList<Int>>): Pair<List<MutableList<Int>>, Int> {
        val vis = mutableSetOf<Pair<Int, Int>>()
        val q = ArrayDeque(state.flatMapIndexed { i, l -> l.mapIndexed { j, _ -> Pair(i, j) } })
        while (q.isNotEmpty()) {
            val current = q.removeFirst()
            val (fi, fj) = current
            state[fi][fj]++
            if (state[fi][fj] < 10) continue
            if (current in vis) continue
            vis.add(current)
            ng(fi, fj, state.size, state[0].size).forEach { q.add(it) }
        }
        return Pair(state.map { l -> l.map { if (it > 9) 0 else it } as MutableList }, vis.size)
    }

    fun part1(input: List<MutableList<Int>>): Int {
        var sum = 0
        var state = input
        for (i in 1..100) {
            val (new_state, count) = flash(state)
            state = new_state
            sum += count
        }
        return sum
    }


    fun part2(input: List<MutableList<Int>>): Int {
        var count = 1
        var state = input
        while (!state.all { it.all { e->e==0 } }) {
            val (new_state, _) = flash(state)
            state = new_state
            count++
        }
        return count
    }

    val input = readInput("Day11").map { it.map { c -> c.code - 48 } as MutableList}
    println(part1(input))
    println(part2(input))
}