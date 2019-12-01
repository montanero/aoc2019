package de.montanero.aoc2019.day01

import de.montanero.aoc2019.util.NumberFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay01b {

    @Test
    fun testExample1() {
        val to = Day01b()
        val result = to.run(listOf(14))
        assertEquals(2, result)
    }

    @Test
    fun testExample2() {
        val to = Day01b()
        val result = to.run(listOf(1969))
        assertEquals(966, result)
    }

    @Test
    fun testExample3() {
        val to = Day01b()
        val result = to.run(listOf(100756))
        assertEquals(50346, result)
    }


    @Test
    fun testData() {
        val nf = NumberFile("/input01.txt")
        val impl = Day01b()
        val result = impl.run(nf.list)
        assertEquals(5115267, result)

        //     5118097 too high
    }

}
