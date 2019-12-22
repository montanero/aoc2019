package de.montanero.aoc2019.day19

import de.montanero.aoc2019.intcode.IntcodeFile
import de.montanero.aoc2019.intcode.IntcodeMachine
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay19b {
    companion object {
        val program = IntcodeFile.read("/input19.txt")
    }


    @Test
    fun test() {
        var start = Pos(100, 0)
        var pos = start
        while (!pos.hasPull())
            pos = Pos(pos.x, pos.y+1)

        pos = pos.findRightMostInBeam()
        while (!Pos(pos.x - 99, pos.y + 99).hasPull()) {
            pos = Pos(pos.x, pos.y + 1).findRightMostInBeam()
        }
        assertEquals(3790981L, (pos.x - 99) * 10000 + pos.y)
    }


    data class Pos(val x: Long, val y: Long) {
        fun hasPull(): Boolean {
            val m = IntcodeMachine(TestDay19b.program)
            m.input.add(x)
            m.input.add(y)
            m.run()
            return m.output.last() != 0L
        }

        fun findRightMostInBeam(): Pos {
            var p = this
            while (true) {
                val np = Pos(p.x + 1, y)
                if (!np.hasPull())
                    return p
                p = np
            }
            return p
        }

    }

}