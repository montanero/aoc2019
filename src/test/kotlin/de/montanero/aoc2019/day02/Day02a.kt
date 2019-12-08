package de.montanero.aoc2019.day02

import de.montanero.aoc2019.intcode.IntcodeMachine

class Day02a {

    fun run(list: MutableList<Int>): List<Int> {
        val m = IntcodeMachine(list);
        m.run()
        return m.memory.toList()
    }

}
