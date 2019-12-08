package de.montanero.aoc2019.day08

import org.junit.jupiter.api.Test

class TestDay08b {
    @Test
    fun test() {
        val image = Image(25, 6, SingleStringReader("/input08.txt").data)
        image.render().forEach {
            println(it)
        }

    }
}