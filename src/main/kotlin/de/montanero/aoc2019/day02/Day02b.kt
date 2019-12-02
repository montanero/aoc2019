package de.montanero.aoc2019.day02

import de.montanero.aoc2019.intcode.IntcodeMachine

class Day02b {

    fun run(list: List<Int>, result: Int): Int {
        for (verb in 0..99) {
            for (noun in 0..99) {
                val i = runOnce(list, verb, noun)
                if (i == result) {
                    return 100*verb+noun
                }
            }
        }
        return 0
    }

    fun runOnce(list: List<Int>, verb: Int, noun: Int): Int {
        val m = IntcodeMachine(list.toMutableList());
        m.memory[1] = verb
        m.memory[2] = noun
        m.run()
        return m.memory[0]
    }

}
