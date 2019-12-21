package de.montanero.aoc2019.day19

import de.montanero.aoc2019.intcode.IntcodeFile
import de.montanero.aoc2019.intcode.IntcodeMachine
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay19 {
    @Test
    fun test() {
        val f = IntcodeFile.read("/input19.txt")
        val count = (0 until 50). map {x ->
            (0 until 50).map {y->
                val m = IntcodeMachine(f)
                m.input.add(x.toLong())
                m.input.add(y.toLong())
                m.run()
                m.output.last()
            }.sum()
        }.sum()
        assertEquals (42L, count)
    }

}