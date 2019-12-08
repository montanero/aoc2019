package de.montanero.aoc2019.day08

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay08a {
    @Test
    fun test ()
    {
        val image = Image(25,6,SingleStringReader("/input08.txt").data)
        val layer = image.layers.minBy { it.count { it==0 } }!!
        val cksum = layer.count{it==1}*layer.count{it==2}
        assertEquals(2684,cksum)

    }
}