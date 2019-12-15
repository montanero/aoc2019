package de.montanero.aoc2019.day13

import de.montanero.aoc2019.intcode.IntcodeFile
import de.montanero.aoc2019.intcode.IntcodeMachine
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TestDay13 {
    @Test
    fun testA() {
        val intcodeMachine = IntcodeMachine(getProgram())
        val cabinet = Cabinet()
        while (!intcodeMachine.stopped) {
            turn(intcodeMachine, cabinet)
        }

        Assertions.assertEquals(284, cabinet.screen.values.count { it == 2 })
        cabinet.printscreen()

    }

    @Test
    fun testB() {
        val intcodeMachine = IntcodeMachine(getProgram())
        intcodeMachine.memory[0] = 2L
        val cabinet = Cabinet()
        while (!intcodeMachine.stopped) {
            if (intcodeMachine.isOnOutput()) {
                updateScreen(intcodeMachine, cabinet);
            } else if (intcodeMachine.isOnInput()) {
                readJoystick(intcodeMachine, cabinet)
                intcodeMachine.step()
            } else
                intcodeMachine.step()
        }

        if (cabinet.screen.values.count { it == 2 } != 0)
            assertTrue(false)
        Assertions.assertEquals(13581, cabinet.score)
    }

    private fun updateScreen(intcodeMachine: IntcodeMachine, cabinet: Cabinet) {
        turn(intcodeMachine, cabinet)
    }

    private fun readJoystick(intcodeMachine: IntcodeMachine, cabinet: Cabinet) {
        val paddleX = cabinet.screen.filter { it.value==3 }.map { it.key.first }.first()!!
        val ballX = cabinet.screen.filter { it.value==4 }.map { it.key.first }.first()!!

        val element = if(paddleX<ballX) 1L else if (paddleX > ballX) -1L else 0
        //cabinet.printscreen()
        //println ("INPUT=${element}")
        intcodeMachine.input.add(element)
        intcodeMachine.step();
    }

    private fun turn(intcodeMachine: IntcodeMachine, cabinet: Cabinet) {
        intcodeMachine.runToOutput()
        val x = intcodeMachine.output.last().toInt();
        intcodeMachine.runToOutput()
        val y = intcodeMachine.output.last().toInt();
        intcodeMachine.runToOutput()
        val tile = intcodeMachine.output.last().toInt();
        cabinet.draw(x, y, tile)
    }

    private fun getProgram(): List<Long> {
        return IntcodeFile.read("/input13.txt")
    }

    class Cabinet {
        val screen = mutableMapOf<Pair<Int, Int>, Int>(Pair(0, 0) to 0)
        var score = 0
        fun draw(x: Int, y: Int, tile: Int) {
            if (x == -1 && y == 0)
                score = tile
            else
                screen[Pair(x, y)] = tile
        }

        fun printscreen() {
            val topLeft = Pair(screen.keys.map { it.first }.min()!!, screen.keys.map { it.second }.min()!!)
            val botRite = Pair(screen.keys.map { it.first }.max()!!, screen.keys.map { it.second }.max()!!)
            for (y in topLeft.second..botRite.second) {
                for (x in topLeft.first..botRite.first) {
                    print(when (screen[Pair(x, y)]) {
                        1 -> '#'
                        2 -> 'X'
                        3 -> '='
                        4 -> 'O'
                        else -> ' '
                    })
                }
                println()

            }
            println("SCORE=${score}")
        }

    }
}
