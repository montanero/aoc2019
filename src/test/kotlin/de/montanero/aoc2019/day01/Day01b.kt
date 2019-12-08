package de.montanero.aoc2019.day01

import kotlin.math.max

class Day01b {
    fun run(list: List<Long>): Long {
        var addfuels = list.map { a -> fuel(a) }
        var f = addfuels
        while (true) {
            addfuels = addfuels.map { a -> fuel(a) }
            if (addfuels.sum() == 0L) {
                return f.sum()
            }
            f = f.zip(addfuels) { a, b -> a + b }
        }
    }

    private fun fuel(a: Long) = max(0, a / 3 - 2)
}
