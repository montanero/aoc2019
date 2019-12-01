package de.montanero.aoc2019.util

class InfiniteArea<T>(private val default: (Int, Int) -> T) {
    private var map: MutableMap<Int, MutableMap<Int, T>> = mutableMapOf()

    operator fun get(x: Int, y: Int): T {
        val line = map[y]
        if (line != null) {
            val value = line[x]
            if (value != null)
                return value
        }
        return default(x, y)
    }

    operator fun set(x: Int, y: Int, value: T) {
        var line = map[y]
        if (line == null) {
            line = mutableMapOf()
            map[y] = line
        }
        line[x] = value
    }

    private fun assertNotEmpty() {
        if (map.isEmpty())
            throw IllegalArgumentException("Array is empty")

    }

    fun leftBoundary(): Int {
        assertNotEmpty()
        return map.values.map { line -> line.keys.min()!! }.min()!!
    }

    fun rightBoundary(): Int {
        assertNotEmpty()
        return map.values.map { line -> line.keys.max()!! }.max()!!
    }

    fun topBoundary(): Int {
        assertNotEmpty()
        return map.keys.min()!!
    }

    fun bottomBoundary(): Int {
        assertNotEmpty()
        return map.keys.max()!!
    }
}
