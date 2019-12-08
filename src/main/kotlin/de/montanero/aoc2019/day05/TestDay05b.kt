package de.montanero.aoc2019.day05

import de.montanero.aoc2019.intcode.IntcodeFile
import de.montanero.aoc2019.intcode.IntcodeMachine
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay05b {
    @Test
    fun testIO() {
        val m = IntcodeMachine(listOf(3,0,4,0,99), listOf(142))
        m.run ()
        assertEquals(142, m.output[0])
    }

    @Test
    fun testData() {
        val m = IntcodeMachine(IntcodeFile.read("/input05.txt"), listOf(5))
        m.run ()
        assertEquals(652726, m.output.last())
    }
}
