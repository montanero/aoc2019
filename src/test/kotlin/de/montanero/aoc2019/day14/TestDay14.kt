package de.montanero.aoc2019.day14

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay14 {
    @Test
    fun testA() {
        val formulas = FormulaFileReader.read("/input14.txt")
        val needed = mutableMapOf("FUEL" to 1)
        while (needed.entries.any { it.key != "ORE" && it.value > 0 }) {
            val produce = needed.filter { it.key != "ORE" && it.value > 0 }.map { it.key }.first()
            val neededUnits = needed[produce]!!
            val formula = formulas.first { it.output.element == produce }
            val formulaUnits = formula.output.amount
            val formulaRuns = (neededUnits + formulaUnits - 1) / formulaUnits
            formula.input.forEach {
                needed[it.element] = needed.getOrDefault(it.element, 0) + it.amount * formulaRuns
            }
            needed[produce] = needed[produce]!! - formulaRuns * formula.output.amount
        }

        Assertions.assertEquals(248794, needed["ORE"])
    }
}