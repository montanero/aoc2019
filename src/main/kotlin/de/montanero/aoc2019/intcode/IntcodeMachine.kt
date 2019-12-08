package de.montanero.aoc2019.intcode

import kotlin.math.pow

class IntcodeMachine(initial: List<Int>, var input: List<Int> = listOf()) {

    val memory = IntcodeMemory(initial)
    val output = mutableListOf<Int>()
    var inputIndex = 0

    var ip: Int = 0;
    fun run() {
        while (!isStopped())
            step();
    }

    private fun step() {
        val instruction = memory[ip]
        val opcode = opcode(instruction)
        val code = opcodes[opcode]
        if (code != null)
            code()
    }

    fun isStopped(): Boolean {
        val opcode = memory[ip]
        return opcode == 99
    }

    fun opcode(instruction: Int): Int {
        return instruction % 100
    }

    private val opcodes = mapOf(
            1 to {
                memory[memory[ip + 3]] = getParam(1) + getParam(2)
                ip += 4
            },
            2 to {
                memory[memory[ip + 3]] = getParam(1) * getParam(2)
                ip += 4
            },
            3 to {
                memory[memory[ip + 1]] = input[inputIndex++]
                ip += 2
            },
            4 to {
                output.add(getParam(1))
                ip += 2
            },
            5 to {
                if (getParam(1) != 0)
                    ip = getParam(2)
                else
                    ip += 3
            },
            6 to {
                if (getParam(1) == 0)
                    ip = getParam(2)
                else
                    ip += 3
            },
            7 to {
                memory[memory[ip + 3]] = if(getParam(1) < getParam(2))1 else 0
                ip += 4
            },
            8 to {
                memory[memory[ip + 3]] = if(getParam(1) == getParam(2))1 else 0
                ip += 4
            },

            99 to {}
    )

    fun getParam(index: Int): Int {
        val instruction = memory[ip]
        val mode = (instruction.toDouble() / 10.0.pow(1.0 + index)).toInt() % 10
        return when (mode) {
            0 -> memory[memory[ip + index]]
            1 -> memory[ip + index]
            else -> throw Exception()
        }
    }

    fun runToOutput() {
        val outputLen = output.size
        while (!isStopped() && outputLen == output.size)
            step();
    }
}