package de.montanero.aoc2019.day03

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay03b {
    @Test
    fun testExample1() {
        val to = Day03()
        val result = to.runB(
                listOf(
                        listOf("R8", "U5", "L5", "D3"),
                        listOf("U7", "R6", "D4", "L4")));

        assertEquals(30, result)
    }

    @Test
    fun testExample2() {
        val to = Day03()
        val result = to.runB(
                listOf(
                        listOf("R75", "D30", "R83", "U83", "L12", "D49", "R71", "U7", "L72"),
                        listOf("U62", "R66", "U55", "R34", "D71", "R55", "D58", "R83")));

        assertEquals(610, result)
    }

    @Test
    fun testExample3() {
        val to = Day03()
        val result = to.runB(
                listOf(
                        listOf("R98", "U47", "R26", "D63", "R33", "U87", "L62", "D20", "R33", "U53", "R51"),
                        listOf("U98", "R91", "D20", "R16", "D67", "R40", "U7", "R15", "U6", "R7")));

        assertEquals(410, result)
    }

    @Test
    fun testData() {
        val to = Day03()
        val nf = LinesFile("/input03.txt")
        val result = to.runB(nf.list)
        assertEquals(11432, result)
    }
}
