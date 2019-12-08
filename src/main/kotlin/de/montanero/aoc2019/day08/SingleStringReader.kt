package de.montanero.aoc2019.day08

class SingleStringReader(resource: String) {

    val data: String


    init {
        val reader = SingleStringReader::class.java.getResourceAsStream(resource).reader(Charsets.UTF_8)
        val sb = StringBuilder()
        reader.use {
            it.forEachLine {
                sb.append(it)
            }
        }
        data = sb.toString().trim()
    }
}