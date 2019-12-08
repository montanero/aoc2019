package de.montanero.aoc2019.intcode

object IntcodeFile {

    fun read(resource: String): List<Int> {
        val reader = IntcodeFile::class.java.getResourceAsStream(resource).reader(Charsets.UTF_8)
        val l: MutableList<Int> = mutableListOf()
        reader.use {
            it.forEachLine {
                it.split(",").forEach {
                    l.add(it.toInt())
                }
            }
        }
        return l
    }
}