package de.montanero.aoc2019.day16

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.abs

class TestDay16 {
    @Test
    fun testA (){
        val input = NumberStringReader.read("/input16.txt")
        val basePattern = listOf(0, 1, 0, -1)
        val patterns = generatePatterns(input.size, basePattern)
        var now = input
        for (i in 1..100){
            now = phase (now, patterns)
        }

        assertEquals ("01234567", stringify(now).substring(0, 8))
    }

    private fun stringify(now: List<Int>) : String {
        return now.joinToString (""){ it.toString() }
    }

    private fun phase(list: List<Int>, patterns: List<List<Int>>): List<Int> {
        return list.indices.map { abs(list.zip(patterns[it]).map { p -> p.first*p.second }.sum())%10 }.toList()
    }

    private fun generatePatterns(size: Int, basePattern: List<Int>) : List<List<Int>> {
        return (1..size ).map { generatePattern(it, size, basePattern) }.toList()
    }

    private fun generatePattern(i: Int, size:Int, basePattern: List<Int>): List<Int> {
        return (1..size).map { basePattern[(it/i)%basePattern.size]}.toList()
    }
}