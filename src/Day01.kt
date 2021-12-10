fun main() {
    fun part1(input: List<Int>): Int {
        return (input zip input.subList(1, input.size)).filter { (a, b) -> a < b }.size
    }

    fun part2(input: List<Int>): Int {
        return (input zip input.subList(3, input.size)).filter { (a, b) -> a < b }.size
    }

    val input = readInput("Day01").map { s -> s.toInt() }
    println(part1(input))
    println(part2(input))
}
