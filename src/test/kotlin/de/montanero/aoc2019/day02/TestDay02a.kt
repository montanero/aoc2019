package de.montanero.aoc2019.day02

import de.montanero.aoc2019.intcode.IntcodeFile
import de.montanero.aoc2019.intcode.IntcodeMachine
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay02a {
    @Test
    fun testExample1() {
        val m = IntcodeMachine(mutableListOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50))
        m.run()
        val result = m.memory[0]
        assertEquals(3500, result)
    }

    @Test
    fun testData() {
        val memory = IntcodeFile.read("/input02.txt").toMutableList()
        memory[1] = 12
        memory[2] = 2
        val m = IntcodeMachine(memory)
        m.run()
        assertEquals(3895705, m.memory[0])
    }
}
