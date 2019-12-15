package de.montanero.aoc2019.day14

object FormulaFileReader{

    val regex = """(\d+) ([A-Z]+)""".toRegex()

    fun read (resource: String) :List<Formula> {
        val list = mutableListOf<Formula>()
        val reader = FormulaFileReader::class.java.getResourceAsStream(resource).reader(Charsets.UTF_8)
        reader.use {
            it.forEachLine {
                val m = regex.findAll(it)
                val chemicals = m.map{
                    Chemical(it.destructured.component2(), it.destructured.component1().toInt())
                }.toList()
                list.add(Formula(chemicals.dropLast(1), chemicals.last()))
            }
        }
        return list
    }
}