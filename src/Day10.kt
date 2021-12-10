fun main() {
    fun validate(s: String): Pair<ArrayDeque<Char>, String> {
        val st = ArrayDeque<Char>()
        val valid = s.takeWhile { ch ->
            if (ch in "{[<(") st.add(ch)
            else st.isNotEmpty() && ("{[<("["}]>)".indexOf(ch)] == st.removeLast())
        }
        return Pair(st, valid)
    }

    fun part1(input: List<String>): Int =
        input.map {
            val (_, valid) = validate(it)
            if (valid.length == it.length) 0
            else when (it[valid.length]) {
                ')' -> 3;']' -> 57;'}' -> 1197;else -> 25137; }
        }.sum()

    fun part2(input: List<String>): Long =
        with(input.map {
            val (st, valid) = validate(it)
            if (valid.length == it.length) {
                var score = 0.toLong()
                while (st.isNotEmpty()) {
                    score = score * 5 + when (st.removeLast()) {
                        '(' -> 1;'[' -> 2;'{' -> 3;else -> 4; }
                }
                score
            } else 0.toLong()
        }.filter { a -> a > 0 }.sorted()) { this[this.size / 2] }

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}