package de.montanero.aoc2019.day06

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay06b {

    @Test
    fun test() {
        val center = OrbitFileReader("/input06.txt").center

        val x1 = center["YOU"]!!
        val x2 = center["SAN"]!!

        var r1 = routeToCenter(center, "YOU")
        var r2 = routeToCenter(center, "SAN")
        while (r1.last() == r2.last()) {
            r1 = r1.dropLast(1)
            r2 = r2.dropLast(1)
        }

        val result = r1.size + r2.size
        assertEquals(253, result)
    }

    private fun routeToCenter(center: Map<String, String>, o: String): List<String> {
        var now = o
        val route = mutableListOf<String>()
        while (center[now] != null) {
            now = center[now]!!
            route.add(now)
        }
        return route
    }


}