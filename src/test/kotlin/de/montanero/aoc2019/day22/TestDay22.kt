package de.montanero.aoc2019.day22

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.Reader

class TestDay22 {

    private val regexCut = """cut (-?\d+)""".toRegex()
    private val regexIdeal = """deal with increment (-?\d+)""".toRegex()
    private val regexDeal = """deal into new stack""".toRegex()


    @Test
    fun testExample() {
        val testCode = """
            deal with increment 7
            deal into new stack
            deal into new stack
        """.trimIndent()

        val deck = shuffle(testCode.reader(), Deck(10))
        Assertions.assertEquals(listOf(0, 3, 6, 9, 2, 5, 8, 1, 4, 7), deck.cards)
    }

    @Test
    fun testA() {
        var deck = Deck(10007)
        val resource = TestDay22::class.java.getResourceAsStream("/input22.txt").reader(Charsets.UTF_8)

        deck = shuffle(resource, deck)
        val idx = deck.cards.indexOf(2019)
        Assertions.assertEquals(1252, idx)
    }

    private fun shuffle(reader: Reader, deck: Deck): Deck {
        var deck1 = deck
        reader.use {
            it.forEachLine { line ->
                val m1 = regexCut.matchEntire(line)
                if (m1 != null) {
                    val count = m1.groupValues[1].toInt()
                    deck1 = deck1.cut(count)
                } else {
                    val m2 = regexIdeal.matchEntire(line)
                    if (m2 != null) {
                        val count = m2.groupValues[1].toInt()
                        deck1 = deck1.dealWithIncrement(count)
                    } else {
                        val m3 = regexDeal.matchEntire(line)
                        if (m3 != null) {
                            deck1 = deck1.deal()
                        } else
                            throw RuntimeException("illegal format")
                    }
                }
            }
        }
        return deck1
    }


    class Deck(val cards: List<Int>) {

        constructor (ncards: Int) : this((0 until ncards).toList())

        fun cut(count: Int): Deck =
                when {
                    count > 0 ->
                        Deck(cards.subList(count, cards.size) + cards.subList(0, count))
                    count < 0 ->
                        cut(cards.size + count)
                    else ->
                        this
                }

        fun dealWithIncrement(count: Int): TestDay22.Deck {
            val rv = arrayOfNulls<Int>(cards.size)
            var idx = 0
            cards.forEach {
                rv[idx] = it
                idx = (idx + count) % cards.size
            }
            return Deck(rv.map { it!! }.toList())
        }

        fun deal(): Deck = Deck(cards.reversed())
    }
}