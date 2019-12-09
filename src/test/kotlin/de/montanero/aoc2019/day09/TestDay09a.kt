package de.montanero.aoc2019.day09

import de.montanero.aoc2019.intcode.IntcodeFile
import de.montanero.aoc2019.intcode.IntcodeMachine
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TestDay09a {
    @Test
    fun test1() {
val p = listOf<Long>(109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99)
        val m = IntcodeMachine (p)
        m.run()
        assertEquals(p, m.output)
    }

    @Test
    fun test2() {

        val m = IntcodeMachine (listOf(1102,34915192,34915192,7,4,7,99,0 ))
        m.run()
        assertTrue(m.output.last()>=1000000000000000L)
        assertTrue(m.output.last()<10000000000000000L)
    }

    @Test
    fun test3() {

        val m = IntcodeMachine (listOf(104,1125899906842624,99 ))
        m.run()
        assertEquals(1125899906842624L, m.output.last())
    }

    @Test
    fun testData() {

        val m = IntcodeMachine (getProgram())
        m.input.add(1L)
        m.run()
        assertEquals(2789104029L, m.output.last())
    }

    @Test
    fun testDataB() {

        val m = IntcodeMachine (getProgram())
        m.input.add(2L)
        m.run()
        assertEquals(32869L, m.output.last())
    }

    private fun getProgram(): List<Long> {
        return IntcodeFile.read("/input09.txt")
    }
}
