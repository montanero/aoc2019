package de.montanero.aoc2019.day02

import java.lang.Math.floor

class Day02a {

    fun run(list: MutableList<Int>): MutableList<Int> {
        val m = Machine (list);
        m.run ()
        return list
    }

}
