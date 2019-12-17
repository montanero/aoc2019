package de.montanero.aoc2019.day16

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalTime
import kotlin.math.abs

class TestDay16b {
    private val basePattern = arrayOf(0, 1, 0, -1)

    @Test
    fun testB() {
        val i0 = NumberStringReader.read("/input16.txt")
        val input = (1..10_000).flatMap { i0 }
        val offset = stringify(i0).substring(0, 7).toInt()
        var now = input
        for (i in 1..100) {
            println("phase $i " + LocalTime.now())
            now = phase(now, offset)
        }

        assertEquals("89950138", stringify(now).substring(offset, offset + 8))
    }


    private fun stringify(now: List<Int>): String {
        return now.joinToString("") { it.toString() }
    }

    private fun phase(list: List<Int>, startFrom: Int): List<Int> {
        val result = mutableListOf<Int>()

        var sum = 0
        for (i in list.size - 1 downTo startFrom) {
            sum += list[i]
            result.add(abs(sum) % 10)
        }

        for (i in 0 until startFrom)
            result.add(0)
        return result.reversed();
    }

    private fun pattern(line: Int, pos: Int): Int = basePattern[((pos + 1) / (line + 1)) % basePattern.size]

}