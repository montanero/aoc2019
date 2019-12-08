package de.montanero.aoc2019.day07

import de.montanero.aoc2019.intcode.IntcodeFile
import de.montanero.aoc2019.intcode.IntcodeMachine
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay07a {
    @Test
    fun testIO() {
        val m = IntcodeMachine(listOf(3, 0, 4, 0, 99))
        m.input.add(142)
        m.run()
        assertEquals(142, m.output[0])
    }

    @Test
    fun test1() {

        assertEquals(43210, calcOutput(0, listOf(4, 3, 2, 1, 0), listOf(3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0)))
    }

    @Test
    fun testData() {

        val x = permutations(listOf(0, 1, 2, 3, 4)).map { settings ->
            calcOutput(0, settings, getProgram())
        }.max()

        assertEquals(212460, x)
    }

    @Test
    fun testDataB() {

        val x = permutations(listOf(5, 6, 7, 8, 9)).map { settings ->
            calcRecursive(settings, getProgram())
        }.max()

        assertEquals(21844737, x)
    }

    private fun calcOutput(input: Int, settings: List<Int>, program: List<Int>): Int {
        var now = input

        settings.forEach {
            val machine = IntcodeMachine(program)
            machine.input.addAll(listOf(it, now))
            machine.run()
            now = machine.output[0]
        }
        return now
    }

    private fun calcRecursive(settings: List<Int>, program: List<Int>): Int {
        var now = 0

        val machines = settings.map {
            val m = IntcodeMachine(program)
            m.input.add(it)
            m;
        }.toList()


        while (!machines.all { it.stopped }) {
            machines.forEach {
                it.input += now
                it.runToOutput()
                now = it.output.last()
            }
        }

        return now
    }

    private fun getProgram(): List<Int> {
        return IntcodeFile.read("/input07.txt")
    }


    fun permutations(inp: List<Int>): List<List<Int>> {
        when (inp.size) {
            0 -> return mutableListOf()
            1 -> return mutableListOf(inp)
            else -> {
                val outp = mutableListOf<List<Int>>()
                for (i in 0..inp.size - 1) {
                    val start = inp[i]
                    val rest = inp.toMutableList()
                    rest.removeAt(i)
                    permutations(rest).forEach {
                        outp.add(listOf(start) + it)
                    }
                }
                return outp
            }
        }
    }
}
