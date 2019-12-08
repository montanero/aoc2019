package de.montanero.aoc2019.day02

import de.montanero.aoc2019.intcode.IntcodeFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay02b {

    @Test
    fun testData() {
        val to = Day02b()
        val result = to.run(IntcodeFile.read("/input02.txt"), 19690720)
        assertEquals(6417, result)
    }
}
