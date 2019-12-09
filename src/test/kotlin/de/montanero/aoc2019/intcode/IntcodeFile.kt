package de.montanero.aoc2019.intcode

object IntcodeFile {

    fun read(resource: String): List<Long> {
        val reader = IntcodeFile::class.java.getResourceAsStream(resource).reader(Charsets.UTF_8)
        val l: MutableList<Long> = mutableListOf()
        reader.use {
            it.forEachLine {
                it.split(",").forEach {
                    l.add(it.toLong())
                }
            }
        }
        return l
    }
}