package de.montanero.aoc2019.intcode;

class IntcodeMemory(initial: List<Long>) {
    private val mem = mutableMapOf<Int,Long>()

    init {
        for (i in 0..initial.size-1)
            mem[i] = initial[i]
    }

    operator fun get(address: Int): Long {
        val x = mem[address]
        return if (x == null) 0 else x
    }

    operator fun set(address: Int, value: Long) {
        mem[address] = value
    }
}
