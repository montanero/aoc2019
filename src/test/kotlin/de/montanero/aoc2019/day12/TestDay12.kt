package de.montanero.aoc2019.day12

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay12 {
    @Test
    fun testA() {
        val positions = PlanetFileReader.read("/input12.txt")
        val planets = positions.map { Planet(it, ThreeDee(0, 0, 0)) }

        for (i in 1..1000) {
            applyGravity(planets)
            applyVelocity(planets)
        }

        val energy = planets.map { it.energy() }.sum()
        assertEquals(42, energy)
    }

    @Test
    fun testExample() {
        val positions = listOf(ThreeDee(-1, 0, 2), ThreeDee(2, -10, -7), ThreeDee(4, -8, 8), ThreeDee(3, 5, -1))
        val planets = positions.map { Planet(it, ThreeDee(0, 0, 0)) }

        for (i in 1..10) {
            applyGravity(planets)
            applyVelocity(planets)
        }

        val energy = planets.map { it.energy() }.sum()
        assertEquals(179, energy)


    }


    private fun applyVelocity(planets: List<Planet>) {
        planets.forEach {
            it.pos = it.pos + it.vel
        }
    }

    private fun applyGravity(planets: List<Planet>) {
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
        planets.zip(newVel).forEach { pair: Pair<Planet, ThreeDee> ->
            pair.first.vel = pair.second
        }
    }

    data class Planet(var pos: ThreeDee, var vel: ThreeDee) {
        fun energy() = potentialEnergy() * kineticEnergy()
        private fun potentialEnergy(): Int = pos.absSum()
        private fun kineticEnergy(): Int = vel.absSum()
    }
}
