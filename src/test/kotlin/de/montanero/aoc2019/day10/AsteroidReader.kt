package de.montanero.aoc2019.day10

object AsteroidReader {

    fun read(resource: String): List<Pair<Int, Int>> {
        val list = mutableListOf<Pair<Int, Int>>()
        val reader = AsteroidReader::class.java.getResourceAsStream(resource).reader(Charsets.UTF_8)
        reader.use {
            var y = 0
            it.forEachLine {
                var x = 0
                it.forEach { c ->
                    if (c == '#') {
                        list.add(x to y)
                    }
                    x++;
                }
                y++
            }
        }
        return list
    }
}