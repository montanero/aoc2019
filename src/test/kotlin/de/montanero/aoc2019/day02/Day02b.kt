package de.montanero.aoc2019.day02

import de.montanero.aoc2019.intcode.IntcodeMachine

class Day02b {

    fun run(list: List<Long>, result: Long): Long {
        for (verb in 0L..99L) {
            for (noun in 0L..99L) {
                val i = runOnce(list, verb, noun)
                if (i == result) {
                    return 100 * verb + noun
                }
            }
        }
        return 0
    }

    fun runOnce(list: List<Long>, verb: Long, noun: Long): Long {
        val m = IntcodeMachine(list.toMutableList());
        m.memory[1] = verb
        m.memory[2] = noun
        m.run()
        return m.memory[0]
    }

}
