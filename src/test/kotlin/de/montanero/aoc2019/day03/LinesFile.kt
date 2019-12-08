package de.montanero.aoc2019.day03

class LinesFile(resource: String) {

    val list: List<List<String>>

    init {
        val reader = LinesFile::class.java.getResourceAsStream(resource).reader(Charsets.UTF_8)
        val l: MutableList<List<String>> = mutableListOf()
        reader.use {
            it.forEachLine {
                val sl = mutableListOf<String>()
                it.split(",").forEach {
                    sl.add(it)
                }
                l.add(sl.toList())
            }
        }

        list = l.toList()
    }
}