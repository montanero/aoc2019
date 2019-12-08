package de.montanero.aoc2019.day06

class OrbitFileReader(resource: String) {

    val center: Map<String, String>
    val regex = """\s*(\S+)\s*\)\s*(\S+)\s*""".toRegex()

    init {
        val reader = OrbitFileReader::class.java.getResourceAsStream(resource).reader(Charsets.UTF_8)
        var l= mutableMapOf<String,String>()
        reader.use {
            it.forEachLine {
                val m = regex.matchEntire(it)
                val (x, y) = m!!.destructured
                l[y] = x
            }

        }
        center = l
    }
}