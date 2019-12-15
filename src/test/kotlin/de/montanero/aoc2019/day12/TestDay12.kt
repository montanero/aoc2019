package de.montanero.aoc2019.day12

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class TestDay12 {
    @Test
    fun testA() {
        val positions = PlanetFileReader.read("/input12.txt")
        var planets = positions.map { Planet(it, ThreeDee(0, 0, 0)) }

        for (i in 1..1000) {
            planets = takeTurn (planets)
        }

        val energy = energy(planets)
        assertEquals(12070, energy)
    }

    private fun takeTurn(planets: List<TestDay12.Planet>): List<TestDay12.Planet> {
        val p2 = applyGravity(planets)
        return applyVelocity(p2)
    }

    @Test
    fun testExample() {
        val positions = listOf(ThreeDee(-1, 0, 2), ThreeDee(2, -10, -7), ThreeDee(4, -8, 8), ThreeDee(3, 5, -1))
        var planets = positions.map { Planet(it, ThreeDee(0, 0, 0)) }

        for (i in 1..10) {
            planets = takeTurn (planets)
        }

        val energy = energy(planets)
        assertEquals(179, energy)


    }

    private fun energy(planets: List<Planet>) =
            planets.map { it.energy() }.sum()

    @Disabled
    @Test
    fun testB():Unit {
        val positions = PlanetFileReader.read("/input12.txt")
        val initials = positions.map { Planet(it, ThreeDee(0, 0, 0)) }
        var planets = initials
        val historicPlanets = mutableMapOf(energy(planets) to 0)


        for (i in 1..1_000_000_000) {
            planets = takeTurn (planets)
            val e = energy(planets)
            if (historicPlanets.contains(e) && planets  == turns(initials, historicPlanets[e]!!)) {
                println(i)
                System.exit(1)
            }
            historicPlanets[e] = i
        }
       val x:Int?  = fail ("not found")
    }

    private fun turns(initials: List<Planet>, turns: Int): List<Planet> {
        var planets = initials
        for (i in 1..turns) {
            planets = takeTurn(planets)
        }
        return planets
    }

    private fun applyVelocity(planets: List<Planet>):List<Planet> {
        return planets.map {
            Planet(it.pos + it.vel, it.vel)
        }
    }

    private fun applyGravity(planets: List<Planet>): List<Planet> {
        val newVel = planets.map { it.vel }.toMutableList()
        for (i in 0..planets.size - 2) {
            val pi = planets[i]
            for (j in i + 1 until planets.size) {
                val pj = planets[j]
                val idiff = (pj.pos - pi.pos).sign()
                newVel[i] = newVel[i] + (idiff)
                newVel[j] = newVel[j] - (idiff)
            }
        }
        return planets.zip(newVel).map { pair: Pair<Planet, ThreeDee> ->
            Planet(pair.first.pos,  pair.second)
        }
    }

    data class Planet(val pos: ThreeDee, val vel: ThreeDee) {
        fun energy() = potentialEnergy() * kineticEnergy()
        private fun potentialEnergy(): Int = pos.absSum()
        private fun kineticEnergy(): Int = vel.absSum()
    }
}
