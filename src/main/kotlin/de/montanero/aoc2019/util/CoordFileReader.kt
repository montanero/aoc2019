package de.montanero.aoc2019.util

class CoordFileReader(resource: String) {

    val list: List<Pair<Int, Int>>
    val regex = """\s*(\d+)\s*,\s*(\d+)\s*""".toRegex()


    init {
        val reader = CoordFileReader::class.java.getResourceAsStream(resource).reader(Charsets.UTF_8)
        var l: MutableList<Pair<Int,Int>> = mutableListOf()
        reader.use {
            it.forEachLine {
                val m = regex.matchEntire(it)
                val (x, y) = m!!.destructured
                l.add (Pair(x.toInt(),y.toInt()))
            }

        }
        list = l.toList();
    }
}