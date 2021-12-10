fun main() {
    fun part1(input: List<String>): Int {
        val sum = input.map { it.map { s -> s.code - 48 } }.reduce { a, b -> a.zip(b, Int::plus) }
        val a = sum.fold(0) { acc, i -> acc * 2 + if (i * 2 > input.size) 1 else 0 }
        val b = sum.fold(0) { acc, i -> acc * 2 + if (i * 2 > input.size) 0 else 1 }
        return a * b
    }

    fun part2(input: List<String>): Int {
        val num = input.map { it.map { s -> s.code - 48 } }
        tailrec fun down(rem: List<List<Int>>, v: Int, acc: Int = 0): Int =
            if (rem[0].isEmpty()) acc
            else {
                val ones = rem.fold(0) { c, l -> c + l[0] }
                val keep = if (ones != rem.size && ones != 0) if (ones * 2 >= rem.size) v else 1 - v else rem[0][0]
                val nRem = rem.filter { l -> l[0] == keep }.map { l -> l.drop(1) }
                down(nRem, v, acc * 2 + keep)
            }

        val a = down(num, 1)
        val b = down(num, 0)
        return a * b
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
