package de.montanero.aoc2019.day01

class StringFileReader(resource: String) {

    val list: List<String>

    init {
        val reader = StringFileReader::class.java.getResourceAsStream(resource).reader(Charsets.UTF_8)
        var l: MutableList<String> = mutableListOf()
        reader.use {
            it.forEachLine {
                l.add(it)
            }

        }
        list = l.toList();
    }
}