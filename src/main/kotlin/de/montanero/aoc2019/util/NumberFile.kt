package de.montanero.aoc2019.util

class NumberFile(resource: String) {

    val list: List<Long>

    init {
        val reader = NumberFile::class.java.getResourceAsStream(resource).reader(Charsets.UTF_8)
        val l: MutableList<Long> = mutableListOf()
        reader.use {
            it.forEachLine {
                l.add(it.toLong())
            }

        }
        list = l.toList()
    }
}