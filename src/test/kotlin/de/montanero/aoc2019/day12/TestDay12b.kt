package de.montanero.aoc2019.day12

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.reflect.KProperty1

class TestDay12b {
    @Test
    fun testB() {
        val positions = PlanetFileReader.read("/input12.txt")
        var planets = positions.map { Planet(it, ThreeDee(0, 0, 0)) }

        val repeatX : Pair<Int,Int> = getCycle ( project(planets, ThreeDee::x))
        val repeatY : Pair<Int,Int> = getCycle ( project(planets, ThreeDee::y))
        val repeatZ : Pair<Int,Int> = getCycle ( project(planets, ThreeDee::z))

        val cycle = kgv (repeatX.second.toLong(), kgv (repeatY.second.toLong(), repeatZ.second.toLong()))

        Assertions.assertEquals(500903629351944L, cycle)

    }

    private fun ggt(x: Long, y: Long): Long = if (y == 0L) x else ggt(y, x % y)
    private fun kgv(x: Long, y: Long): Long {
        val ggt = ggt (x,y)
        return x/ggt * y/ggt
    }


    private fun project(planets: List<Planet>, getCoordinate: KProperty1<ThreeDee, Int>): List<PlanetProjection> = planets.map { PlanetProjection(getCoordinate.invoke(it.pos), getCoordinate.invoke(it.vel)) }.toList()


    private fun takeTurn(planets: List<TestDay12b.PlanetProjection>): List<TestDay12b.PlanetProjection> {
        val p2 = applyGravity(planets)
        return applyVelocity(p2)
    }


    private fun getCycle(initials: List<PlanetProjection>): Pair<Int, Int> {
        val map = mutableMapOf<List<PlanetProjection>, Int>()
        map[initials] = 0
        var turn = 1
        var planets = initials
        while (true)
        {
            planets = takeTurn(planets)
            val hit = map[planets]
            if (hit != null) {
                return Pair(hit, turn)
            }
            map[planets] = turn++
        }
    }

    private fun applyVelocity(planets: List<PlanetProjection>): List<PlanetProjection> {
        return planets.map {
            PlanetProjection(it.pos + it.vel, it.vel)
        }
    }

    private fun applyGravity(planets: List<PlanetProjection>): List<PlanetProjection> =
            planets.map { p ->
                PlanetProjection(
                        p.pos,
                        p.vel + planets.map { sign(it.pos - p.pos) }.sum())
            }.toList()

    private fun sign(a: Int) = if (a > 0) 1 else if (a < 0) -1 else 0

    data class Planet(val pos: ThreeDee, val vel: ThreeDee)
    data class PlanetProjection(val pos: Int, val vel: Int)
}
