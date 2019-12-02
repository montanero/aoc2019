package de.montanero.aoc2019.util

class MultiNumberFile(resource: String) {

    val list: List<Int>

    init {
        val reader = MultiNumberFile::class.java.getResourceAsStream(resource).reader(Charsets.UTF_8)
        val l: MutableList<Int> = mutableListOf()
        reader.use {
            it.forEachLine {
                it.split(",").forEach {
                    l.add(it.toInt())
                }

            }
        }

        list = l.toList()
    }
}