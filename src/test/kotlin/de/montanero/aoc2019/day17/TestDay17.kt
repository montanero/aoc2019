package de.montanero.aoc2019.day17

import de.montanero.aoc2019.intcode.IntcodeFile
import de.montanero.aoc2019.intcode.IntcodeMachine
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Route L,8,R,10,L,10,R,10,L,8,L,8,L,10,L,8,R,10,L,10,L,4,L,6,L,8,L,8,R,10,L,8,L,8,L,10,L,4,L,6,L,8,L,8,L,8,R,10,L,10,L,4,L,6,L,8,L,8,R,10,L,8,L,8,L,10,L,4,L,6,L,8,L,8

class TestDay17 {


    @Test
    fun testA() {
        val maze = explore();
        maze.printArea()
        var sum = 0
        for (y in 1..maze.area.size - 2) {
            for (x in 1..maze.area[0].length - 2) {
                if (maze.isCrossing(x, y))
                    sum += x * y
            }
        }
        assertEquals(5940, sum)

    }

    @Test
    fun testB() {
        val M="C,A,C,B,A,B,C,B,A,B"
        val A="R,10,L,8,L,8,L,10"
        val B="L,4,L,6,L,8,L,8"
        val C="L,8,R,10,L,10"
        val D=""
        val NO="n"
        val LF=10.toChar()
        val input = (M+LF+A+LF+B+LF+C+LF+D+LF+NO+LF).map { it.toLong() }.toList()

        val m = IntcodeMachine(IntcodeFile.read("/input17.txt"))
        m.memory[0] = 2
        m.input.addAll(input)
        m.run();
        assertEquals (923795, m.output.last())


    }


    fun explore(): Maze {
        var maze = Maze()
        val m = IntcodeMachine(IntcodeFile.read("/input17.txt"))
        m.run();

        var sb = StringBuffer()
        m.output.forEach {
            if (it == 10L) {
                maze.area.add(sb.toString())
                sb = StringBuffer()
            } else {
                sb.append(it.toChar())
            }
        }
        return maze
    }

    class Maze {
        var area = mutableListOf<String>()

        fun printArea() {
            area.forEach {
                println(it)
            }
        }

        fun isWall(x: Int, y: Int): Boolean =
                area[y][x] != '.'

        fun isCrossing(x: Int, y: Int): Boolean =
                isWall(x, y) && isWall(x - 1, y) && isWall(x + 1, y) && isWall(x, y - 1) && isWall(x, y + 1)

    }

}