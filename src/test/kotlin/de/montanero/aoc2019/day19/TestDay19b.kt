package de.montanero.aoc2019.day19

import de.montanero.aoc2019.intcode.IntcodeFile
import de.montanero.aoc2019.intcode.IntcodeMachine
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay19b {
    val f = IntcodeFile.read("/input19.txt")

    @Test
    fun test() {
        val count = (0 until 50). forEach {x ->
            (0 until 50).forEach {y->
                val m = IntcodeMachine(f)
                m.input.add(x.toLong())
                m.input.add(y.toLong())
                m.run()
                print(if (m.output.last()!= 0L) "#" else ".")
            }
            println ()
        }
        assertEquals (42L, count)
    }

}