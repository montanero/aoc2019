package de.montanero.aoc2019.intcode

import java.lang.Exception

class IntcodeMachine(initial: List<Long>) {

    val memory = IntcodeMemory(initial)
    val output = mutableListOf<Long>()
    val input = mutableListOf<Long>()
    var stopped: Boolean = false
    var ip: Int = 0;
    var rb: Int = 0;

    fun run() {
        while (!stopped)
            step();
    }

    fun runToOutput() {
        val outputLen = output.size
        while (!stopped && outputLen == output.size)
            step();
    }

    fun runToInput() {
        while (true) {
            if (stopped)
                return;
            val instruction = Instruction(memory[ip])
            if (instruction.opcode == Opcode.INPUT)
                return
            step()
        }
    }

    fun step() {
        val instruction = Instruction(memory[ip])
        instruction.execute()
    }

    fun isOnOutput(): Boolean {
        val instruction = Instruction(memory[ip])
        return instruction.opcode == Opcode.OUTPUT
    }

    fun isOnInput(): Boolean {
        val instruction = Instruction(memory[ip])
        return instruction.opcode == Opcode.INPUT
    }

    private enum class Opcode(val numericCode: Long) {
        ADD(1),
        MULT(2),
        INPUT(3),
        OUTPUT(4),
        JUMP_IF_TRUE(5),
        JUMP_IF_FALSE(6),
        LESS_THEN(7),
        EQUALS(8),
        RELATIVE_BASE_OFFSET(9),
        STOP(99)
    }

    private enum class OperandMode(val numericCode: Long) {
        POSITION(0),
        IMMEDIATE(1),
        RELATIVE(2)
    }

    private inner class Instruction(val instruction: Long) {
        val opcode: Opcode = Opcode.values().find { it.numericCode == instruction % 100 }!!
        val opModes: List<OperandMode> = getOperandModes()

        fun getOperandMode(index: Int): OperandMode {
            return if (index - 1 < opModes.size)
                opModes[index - 1]
            else
                OperandMode.POSITION
        }

        fun getOperandModes(): List<OperandMode> {
            val opModess = mutableListOf<OperandMode>()
            var i = instruction / 100
            while (i != 0L) {
                opModess += OperandMode.values().find { it.numericCode == i % 10 }!!
                i /= 10
            }
            return opModess.toList()
        }

        fun execute(): Unit {
            when (opcode) {
                Opcode.ADD -> {
                    setParam(3,  getParam(1) + getParam(2))
                    ip += 4
                }
                Opcode.MULT -> {
                    setParam(3,   getParam(1) * getParam(2))
                    ip += 4
                }
                Opcode.INPUT -> {
                    setParam(1,  input.removeAt(0))
                    ip += 2
                }
                Opcode.OUTPUT -> {
                    output.add(getParam(1))
                    ip += 2
                }
                Opcode.JUMP_IF_TRUE -> {
                    if (getParam(1) != 0L)
                        ip = getParam(2).toInt()
                    else
                        ip += 3
                }
                Opcode.JUMP_IF_FALSE -> {
                    if (getParam(1) == 0L)
                        ip = getParam(2).toInt()
                    else
                        ip += 3
                }
                Opcode.LESS_THEN -> {
                    setParam(3,   if (getParam(1) < getParam(2)) 1 else 0)
                    ip += 4
                }
                Opcode.EQUALS -> {
                    setParam(3,   if (getParam(1) == getParam(2)) 1 else 0)
                    ip += 4
                }
                Opcode.RELATIVE_BASE_OFFSET -> {
                    rb += getParam(1).toInt()
                    ip += 2
                }

                Opcode.STOP -> {
                    stopped = true
                }
            }
        }


        fun getParam(index: Int): Long {
            return when (getOperandMode(index)) {
                OperandMode.POSITION -> memory[memory[ip + index].toInt()]
                OperandMode.RELATIVE -> memory[memory[ip + index].toInt()+rb]
                OperandMode.IMMEDIATE -> memory[ip + index]
            }
        }

        fun setParam(index: Int, val_:Long): Unit {
            when (getOperandMode(index)) {
                OperandMode.POSITION -> { memory[memory[ip + index].toInt()] = val_ }
                OperandMode.RELATIVE -> { memory[memory[ip + index].toInt()+rb] = val_ }
                OperandMode.IMMEDIATE -> throw Exception()
            }
        }
    }
}

