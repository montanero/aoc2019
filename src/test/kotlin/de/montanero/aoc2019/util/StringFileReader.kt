package de.montanero.aoc2019.day01

object StringFileReader{

    fun read (resource: String) : List<String> {
        val reader = StringFileReader::class.java.getResourceAsStream(resource).reader(Charsets.UTF_8)
        reader.use {
            it.useLines {return it.toList()}
        }
    }
}