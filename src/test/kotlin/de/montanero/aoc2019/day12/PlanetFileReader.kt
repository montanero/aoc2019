package de.montanero.aoc2019.day12

object PlanetFileReader{

    val regex = """\s*<x=(-?\d+),\s*y=(-?\d+),\s*z=(-?\d+)>""".toRegex()


    fun read (resource: String) :List<ThreeDee> {
        val list = mutableListOf<ThreeDee>()
        val reader = PlanetFileReader::class.java.getResourceAsStream(resource).reader(Charsets.UTF_8)
        reader.use {
            it.forEachLine {
                val m = regex.matchEntire(it)
                val (x, y, z) = m!!.destructured
                list.add(ThreeDee(x.toInt(), y.toInt(), z.toInt()))
            }
        }
        return list
    }
}