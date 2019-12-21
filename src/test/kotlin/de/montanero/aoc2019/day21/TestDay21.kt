package de.montanero.aoc2019.day21

import de.montanero.aoc2019.intcode.IntcodeFile
import de.montanero.aoc2019.intcode.IntcodeMachine
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay21 {

    @Test
    fun testA() {
        val commands = listOf(
                "NOT A J",
                "NOT B T",
                "OR T J",
                "NOT C T",
                "OR T J",
                "AND D J", // jump to solid ground
                "WALK"
        )
        val m = IntcodeMachine(IntcodeFile.read("/input21.txt"))
        commands.forEach {
            sendCommand(m, it);
        }
        m.run();
        //showDisplay(m);
        val l = m.output.last()
        assertEquals (19354890, l)

    }

    @Test
    fun testB() {
        val commands = listOf(
                // Loch in ABC
                "NOT A J",
                "NOT B T",
                "OR T J",
                "NOT C T",
                "OR T J",
                // Fester grund in D
                "AND D J",
                // Loch in E
                "NOT E T",
                "AND H J",
                "OR A J",
                "RUN"
        )
        val m = IntcodeMachine(IntcodeFile.read("/input21.txt"))
        commands.forEach {
            sendCommand(m, it);
        }
        m.run();
        showDisplay(m);
        val l = m.output.last()
        assertEquals (19354890, l)

    }

    fun showDisplay(m: IntcodeMachine) {
        val l = getDisplay(m)
        l.forEach {
            println(it)
        }
    }


    fun getDisplay(m: IntcodeMachine): List<String> {
        val display = mutableListOf<String>()
        var sb = StringBuffer()
        m.output.forEach {
            if (it == 10L) {
                display.add(sb.toString())
                sb = StringBuffer()
            } else {
                sb.append(it.toChar())
            }
        }
        return display
    }

    fun sendCommand(m: IntcodeMachine, s: String) {
        s.forEach { m.input.add(it.toLong()) }
        m.input.add(10L)
    }
}