package de.montanero.aoc2019.day02

import de.montanero.aoc2019.util.MultiNumberFile
import de.montanero.aoc2019.util.NumberFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay02b {

    @Test
    fun testData() {
        val to = Day02b()
        val nf = MultiNumberFile("/input02.txt")
        val result = to.run(nf.list, 19690720)
        assertEquals(3500, result)
    }
}
