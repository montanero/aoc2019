package de.montanero.aoc2019.day16

object NumberStringReader{

    fun read (resource: String): List<Int> {
        val reader = NumberStringReader::class.java.getResourceAsStream(resource).reader(Charsets.UTF_8)
        val sb = StringBuilder()
        reader.use {
            it.forEachLine {
                sb.append(it)
            }
        }
        return sb.map { (it-'0').toInt() }
    }
}