package de.montanero.aoc2019.day10

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.abs
import kotlin.math.sign

class TestDay10 {
    @Test
    fun testA() {
        val asteroids = AsteroidReader.read("/input10.txt")
        val x = asteroids.maxBy { it -> visibleFrom(asteroids, it) }!!
        assertEquals(314, visibleFrom(asteroids, x))
    }

    private fun visibleFrom(asteroids: List<Pair<Int, Int>>, origin: Pair<Int, Int>): Int {
        val m = asteroids.filter { it != origin }.map { Direction.make(it, origin) }
        val s = m.toSet()
        return s.count()
    }

    @Test
    fun testB() {

        val station = 27 to 19
        val asteroids = AsteroidReader.read("/input10.txt")
        val m = asteroids
                .filter { it != station }
                .map { PolarCoordinates.make(it, station) }
                .sorted().toMutableList()

        var count = 0
        var nth: Pair<Int,Int> = Pair(0,0)
        for (d in m.map { it.dir }.toSortedSet()) {
            val x = m.find { it.dir == d }!!
            m.remove(x)
            if (++count == 200) {
                nth = asteroids.filter { it != station && PolarCoordinates.make(it, station) == x }.first()
                break
            }
        }

        assertEquals(1513, nth.first*100+nth.second)
    }

    data class Direction(val vx: Int,
                         val vy: Int) : Comparable<Direction> {


        companion object Helper {

            fun make(absPoint: Pair<Int, Int>, absOrigin: Pair<Int, Int>): Direction {
                val dx = absPoint.first - absOrigin.first
                val dy = absOrigin.second - absPoint.second
                val g = ggt(abs(dx), abs(dy))
                return Direction(dx / g,
                        dy / g)
            }

            private fun ggt(x: Int, y: Int): Int {
                return if (y == 0) x else ggt(y, x % y)
            }
        }


        override fun compareTo(other: Direction): Int {
            val q1 = quadrant()
            val q2 = other.quadrant()
            return when {
                (q1 < q2) -> -1
                (q1 > q2) -> 1
                (this == other) -> 0
                else -> sign(abs(other.vx.toDouble() / other.vy) - abs(vx.toDouble() / vy)).toInt()
            }
        }

        private fun quadrant(): Int {
            return if (vx >= 0) {
                if (vy >= 0) 1 else 2
            } else {
                if (vy >= 0) 4 else 3
            }
        }
    }

    data class PolarCoordinates(val dir: Direction, val dist: Int) : Comparable<PolarCoordinates> {
        companion object Helper {

            fun make(absPoint: Pair<Int, Int>, absOrigin: Pair<Int, Int>): PolarCoordinates {
                val dx = absPoint.first - absOrigin.first
                val dy = absOrigin.first - absPoint.first
                return PolarCoordinates(
                        Direction.make(absPoint, absOrigin),
                        abs(dx) + abs(dy))
            }
        }

        override fun compareTo(other: PolarCoordinates): Int {
            val c = dir.compareTo(other.dir)
            return if (c != 0)
                c
            else
                dist - other.dist
        }

    }
}
