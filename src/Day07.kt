import kotlin.math.abs

fun main() {
    fun part1(input: List<Int>): Int {
        val median = input.sorted()[input.size/2]
        return input.sumOf { abs(it-median) }
    }

    fun part2(input: List<Int>): Int {
        val mean = input.sum()/input.size
        return input.sumOf { abs(it-mean)*(abs(it-mean)+1)/2 }
    }
    val input = readInput("Day07")[0].split(",").map(String::toInt)
    println(part1(input))
    println(part2(input))
}