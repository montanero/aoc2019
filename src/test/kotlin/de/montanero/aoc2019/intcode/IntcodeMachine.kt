package de.montanero.aoc2019.intcode

class IntcodeMachine(initial: List<Int>) {

    val memory = IntcodeMemory(initial)
    val output = mutableListOf<Int>()
    val input = mutableListOf<Int>()
    var stopped: Boolean = false
    var ip: Int = 0;
    fun run() {
        while (!stopped)
            step();
    }

    fun runToOutput() {
        val outputLen = output.size
        while (!stopped && outputLen == output.size)
            step();
    }

    private fun step() {
        val instruction = Instruction(memory[ip])
        instruction.execute()
    }

    private enum class Opcode(val numericCode: Int) {
        ADD(1),
        MULT(2),
        INPUT(3),
        OUTPUT(4),
        JUMP_IF_TRUE(5),
        JUMP_IF_FALSE(6),
        LESS_THEN(7),
        EQUALS(8),
        STOP(99)
    }

    enum class OperandMode(val numericCode: Int) {
        POSITION(0),
        IMMEDIATE(1)
    }

    inner class Instruction(val instruction: Int) {
        private val opcode: Opcode = Opcode.values().find { it.numericCode == instruction % 100 }!!
        private val opModes: List<OperandMode> = getOperandModes()

        fun getOperandMode(index: Int): OperandMode {
            return if (index - 1 < opModes.size)
                opModes[index - 1]
            else
                OperandMode.POSITION
        }

        private fun getOperandModes(): List<OperandMode> {
            val opModess = mutableListOf<OperandMode>()
            var i = instruction / 100
            while (i != 0) {
                opModess += OperandMode.values().find { it.numericCode == i % 10 }!!
                i /= 10
            }
            return opModess.toList()
        }

        fun execute(): Unit {
            when (opcode) {
                Opcode.ADD -> {
                    memory[memory[ip + 3]] = getParam(1) + getParam(2)
                    ip += 4
                }
                Opcode.MULT -> {
                    memory[memory[ip + 3]] = getParam(1) * getParam(2)
                    ip += 4
                }
                Opcode.INPUT -> {
                    memory[memory[ip + 1]] = input.removeAt(0)
                    ip += 2
                }
                Opcode.OUTPUT -> {
                    output.add(getParam(1))
                    ip += 2
                }
                Opcode.JUMP_IF_TRUE -> {
                    if (getParam(1) != 0)
                        ip = getParam(2)
                    else
                        ip += 3
                }
                Opcode.JUMP_IF_FALSE -> {
                    if (getParam(1) == 0)
                        ip = getParam(2)
                    else
                        ip += 3
                }
                Opcode.LESS_THEN -> {
                    memory[memory[ip + 3]] = if (getParam(1) < getParam(2)) 1 else 0
                    ip += 4
                }
                Opcode.EQUALS -> {
                    memory[memory[ip + 3]] = if (getParam(1) == getParam(2)) 1 else 0
                    ip += 4
                }

                Opcode.STOP -> {
                    stopped = true
                }
            }
        }


        fun getParam(index: Int): Int {
            return when (getOperandMode(index)) {
                OperandMode.POSITION -> memory[memory[ip + index]]
                OperandMode.IMMEDIATE -> memory[ip + index]
            }
        }
    }
}

