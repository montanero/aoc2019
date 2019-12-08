package de.montanero.aoc2019.intcode;

class IntcodeMemory(initial: List<Int>) {
    private val mem = initial.toMutableList()

    operator fun get(address: Int): Int {
        return mem[address]
    }

    operator fun set(address: Int, value: Int) {
        mem[address] = value
    }

    fun toList(): List<Int> {
        return mem.toList()
    }

}
