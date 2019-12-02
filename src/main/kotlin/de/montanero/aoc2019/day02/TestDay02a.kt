package de.montanero.aoc2019.day02

import de.montanero.aoc2019.util.MultiNumberFile
import de.montanero.aoc2019.util.NumberFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay02a {
    @Test
    fun testExample1() {
        val to = Day02a()
        val result = to.run(mutableListOf(1,9,10,3,2,3,11,0,99,30,40,50))
        assertEquals(3500, result[0])
    }

    @Test
    fun testData() {
        val to = Day02a()
        val nf = MultiNumberFile("/input02.txt")
        val memory = nf.list.toMutableList()
memory[1] = 12
memory[2] = 2
        val result = to.run(memory)
        assertEquals(3500, result[0])
    }
}
