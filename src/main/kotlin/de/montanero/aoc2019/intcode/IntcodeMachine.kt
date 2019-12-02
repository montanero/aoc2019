package de.montanero.aoc2019.intcode

class IntcodeMachine( initial: List<Int>) {

    val memory = IntcodeMemory(initial)

    var ip: Int = 0;
    fun run() {
        while (!isStopped())
            step();
    }

    private fun step() {
        val opcode = memory[ip]
        val code = opcodes[opcode]
        if (code != null)
            code()
    }

    fun isStopped(): Boolean {
        val opcode = memory[ip]
        return opcode == 99
    }

    private val opcodes = mapOf(
            1 to {
                memory[memory[ip + 3]] = memory[memory[ip + 1]] + memory[memory[ip + 2]]
                ip += 4
            },
            2 to {
                memory[memory[ip + 3]] = memory[memory[ip + 1]] * memory[memory[ip + 2]]
                ip += 4
            },
            99 to {}
    )
}