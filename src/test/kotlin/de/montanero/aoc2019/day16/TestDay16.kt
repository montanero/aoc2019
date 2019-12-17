package de.montanero.aoc2019.day16

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalTime
import kotlin.math.abs

class TestDay16 {
    private val basePattern = arrayOf(0, 1, 0, -1)

    @Test
    fun testA() {
        val input = NumberStringReader.read("/input16.txt")
        var now = input
        for (i in 1..100) {
            now = phase(now)
        }

        assertEquals("40921727", stringify(now).substring(0, 8))
    }

    private fun stringify(now: List<Int>): String {
        return now.joinToString("") { it.toString() }
    }

    private fun phase(list: List<Int>): List<Int> {
        return list.indices
                .map { i ->
                    abs(list.mapIndexed { j, p ->
                        p * pattern(i, j)
                    }.sum()) % 10
                }.toList()
    }

    private fun pattern(line: Int, pos: Int): Int = basePattern[((pos + 1) / (line + 1)) % basePattern.size]

}