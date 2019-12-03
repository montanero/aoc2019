package de.montanero.aoc2019.day03

import kotlin.math.abs

class Day03a {

    fun run(list: List<List<String>>): Int {
        val l1 = drawLine(list[0])
        val l2 = drawLine(list[1])

        val crossings = mutableListOf<Point>()

        var here1 = l1[0]
        l1.subList(1, l1.size).forEach { there1 ->
            var here2 = l2[0]
            l2.subList(1, l2.size).forEach { there2 ->
                val c = Line(here1, there1).crossing(Line(here2, there2))
                    crossings.addAll(c)
                here2 = there2
            }
            here1 = there1
        }

        return crossings.filter { it != (Point(0,0))}.map { p -> abs(p.x) + abs(p.y) }.sorted().first()
    }

    fun drawLine(directions: List<String>): List<Point> {
        val result = mutableListOf<Point>()
        var here = Point(0, 0);
        result.add(here)
        directions.forEach {
            val dist = it.substring(1).toInt()
            val dir = it[0]
            here = when (dir) {
                'R' -> here.right(dist)
                'L' -> here.left(dist)
                'U' -> here.up(dist)
                'D' -> here.down(dist)
                else -> throw Exception()
            }
            result.add(here)
        }
        return result
    }
}

class Point(val x: Int, val y: Int) {
    fun right(d: Int): Point {
        return Point(x + d, y)
    }

    fun left(d: Int): Point {
        return Point(x - d, y)
    }

    fun up(d: Int): Point {
        return Point(x, y + d)
    }

    fun down(d: Int): Point {
        return Point(x, y - d)
    }

    override fun toString(): String {
        return "Point(x=$x, y=$y)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}

class Line(val p1: Point, val p2: Point) {
    fun horizontal(): Boolean {
        return p1.y == p2.y
    }

    fun contains(x1: Int, x2: Int, x3: Int): Boolean {
        if (x1 > x2)
            return contains(x2, x1, x3)
        return x3 >= x1 && x3 <= x2
    }

    fun crossing(line: Line): List<Point> {
        if (horizontal()) {
            if (!line.horizontal()) {
                if (contains(p1.x, p2.x, line.p1.x) && contains(line.p1.y, line.p2.y, p1.y))
                    return listOf(Point(line.p1.x, p1.y))
            } else {
                val result = mutableListOf<Point>()
                if (p1.y == line.p1.y) {
                    if (contains(p1.x, p2.x, line.p1.x))
                        result.add(line.p1)
                    if (contains(p1.x, p2.x, line.p2.x))
                        result.add(line.p2)
                    if (contains(line.p1.x, line.p2.x, p1.x))
                        result.add(p1)
                    if (contains(line.p1.x, line.p2.x, p2.x))
                        result.add(p2)
                    return result
                }
            }
        } else {
            if (line.horizontal()) {
                return line.crossing(this)
            } else {
                if (p1.x == line.p1.x) {
                    val result = mutableListOf<Point>()
                    if (contains(p1.y, p2.y, line.p1.y))
                        result.add(line.p1)
                    if (contains(p1.y, p2.y, line.p2.y))
                        result.add(line.p2)
                    if (contains(line.p1.y, line.p2.y, p1.y))
                        result.add(p1)
                    if (contains(line.p1.y, line.p2.y, p2.y))
                        result.add(p2)
                    return result
                }
            }
        }
        return listOf<Point>()

    }

    override fun toString(): String {
        return "Line(p1=$p1, p2=$p2)"
    }
}