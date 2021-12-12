fun main() {
    fun count(path: List<String>, ng: MutableMap<String, MutableList<String>>): Int =
        if (path.last() == "end") 1
        else ng[path.last()]!!.sumOf {
            if (it in path) 0
            else count(path + listOf(it), ng)
        }

    fun count2(path: List<String>, ng: MutableMap<String, MutableList<String>>, th: Boolean = false): Int =
        if (path.last() == "end") 1
        else ng[path.last()]!!.sumOf {
            if (it in path) {
                if (th || it == "start") 0
                else count2(path + listOf(it), ng, true)
            } else count2(path + listOf(it), ng, th)
        }

    fun getNg(input: List<Pair<String, String>>): MutableMap<String, MutableList<String>> {
        val ng = mutableMapOf<String, MutableList<String>>()
        input.forEach { (a, b) ->
            ng.putIfAbsent(a, mutableListOf())
            ng.putIfAbsent(b, mutableListOf())
        }
        input.forEach { (a, b) ->
            if (a.uppercase() == a) ng[a]!!.add(b)
            else if (b.uppercase() == b) ng[b]!!.add(a)
            else {
                ng[a]!!.add(b)
                ng[b]!!.add(a)
            }
        }
        ng.forEach { (k, v) ->
            if (k.uppercase() == k) {
                v.flatMap { v1 -> v.map { v2 -> Pair(v1, v2) } }.forEach { (a, b) ->
                    if (a < b) {
                        ng[a]!!.add(b)
                        ng[b]!!.add(a)
                    } else if (a == b) {
                        ng[a]!!.add(b)
                    }
                }
            }
        }
        return ng
    }

    fun part1(input: List<Pair<String, String>>): Int = count(listOf("start"), getNg(input))


    fun part2(input: List<Pair<String, String>>): Int = count2(listOf("start"), getNg(input))


    val input = readInput("Day12").map { it.split("-") }.map { Pair(it[0], it[1]) }
    println(part1(input))
    println(part2(input))
}