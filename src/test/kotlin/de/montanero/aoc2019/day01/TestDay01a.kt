package de.montanero.aoc2019.day01

import de.montanero.aoc2019.util.NumberFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay01a {
    @Test
    fun testExample1() {
        val to = Day01a()
        val result = to.run(listOf(12))
        assertEquals(2, result)
    }

    @Test
    fun testExample2() {
        val to = Day01a()
        val result = to.run(listOf(14))
        assertEquals(2, result)
    }

    @Test
    fun testExample3() {
        val to = Day01a()
        val result = to.run(listOf(1969))
        assertEquals(654, result)
    }

    @Test
    fun testExample4() {
        val to = Day01a()
        val result = to.run(listOf(100756))
        assertEquals(33583, result)
    }

    @Test
    fun testData() {
        val nf = NumberFile("/input01.txt")
        val impl = Day01a()
        val result = impl.run(nf.list)
        assertEquals(3412094, result)
    }

}
