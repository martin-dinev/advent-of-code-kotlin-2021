fun main() {
    fun part1(input: List<Int>, days: Int = 80): Long {
        var cnt = MutableList<Long>(9) { 0 }
        for (e in input) cnt[e]++
        for (e in 1..days) {
            val b = cnt[0]
            cnt = cnt.drop(1) as MutableList<Long>
            cnt[6] += b
            cnt.add(b)
        }
        return cnt.sum()
    }

    fun part2(input: List<Int>): Long = part1(input, 256)

    val input = readInput("Day06")[0].split(",").map(String::toInt)
    println(part1(input))
    println(part2(input))
}