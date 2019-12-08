package de.montanero.aoc2019.day06

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay06a {

    @Test
    fun test() {
        val center = OrbitFileReader("/input06.txt").center

        val norbits = center.keys.map { orbits(center, it) }.sum()
        assertEquals(106065, norbits)
    }

    fun orbits(center: Map<String, String>, o: String): Int {
        val c = center[o]
        return if (c == null) 0 else 1 + orbits(center, c)
    }


}