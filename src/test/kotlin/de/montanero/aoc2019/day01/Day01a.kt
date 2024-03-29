package de.montanero.aoc2019.day01

class Day01a {
    fun run(list: List<Long>): Long {
        return list.map { a -> a / 3 - 2 }.fold(0L, { a, b -> a + b })
    }

}
