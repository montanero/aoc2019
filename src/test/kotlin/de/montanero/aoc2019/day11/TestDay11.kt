package de.montanero.aoc2019.day11

import de.montanero.aoc2019.intcode.IntcodeFile
import de.montanero.aoc2019.intcode.IntcodeMachine
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay11 {
    @Test
    fun testA() {
        val machine = IntcodeMachine(getProgram())
        val robot = Robot()

        while (!machine.stopped) {
            machine.input.add(robot.color().toLong())
            machine.runToOutput()
            robot.paint(machine.output.last().toInt())
            machine.runToOutput()
            if (machine.output.last() == 0L)
                robot.turnLeft()
            else
                robot.turnRight()
            robot.move()
        }

        Assertions.assertEquals(2088, robot.area.size)


    }

    @Test
    fun testB() {
        val machine = IntcodeMachine(getProgram())
        val robot = Robot()
        robot.area[Pair(0, 0)] = 1
        while (!machine.stopped) {
            machine.input.add(robot.color().toLong())
            machine.runToOutput()
            robot.paint(machine.output.last().toInt())
            machine.runToOutput()
            if (machine.output.last() == 0L)
                robot.turnLeft()
            else
                robot.turnRight()
            robot.move()
        }
        robot.printArea()
    }

    private fun getProgram(): List<Long> {
        return IntcodeFile.read("/input11.txt")
    }

    class Robot {
        var pos = Pair(0, 0)
        var direction = Direction.UP
        var area = mutableMapOf<Pair<Int, Int>, Int>()

        enum class Direction {
            UP, RIGHT, DOWN, LEFT
        }

        fun turnLeft() {
            direction = when (direction) {
                Direction.UP -> Direction.LEFT
                Direction.RIGHT -> Direction.UP
                Direction.DOWN -> Direction.RIGHT
                Direction.LEFT -> Direction.DOWN
            }
        }

        fun turnRight() {
            direction = when (direction) {
                Direction.UP -> Direction.RIGHT
                Direction.RIGHT -> Direction.DOWN
                Direction.DOWN -> Direction.LEFT
                Direction.LEFT -> Direction.UP
            }
        }

        fun move() {
            pos = when (direction) {
                Direction.UP -> Pair(pos.first, pos.second - 1)
                Direction.RIGHT -> Pair(pos.first + 1, pos.second)
                Direction.DOWN -> Pair(pos.first, pos.second + 1)
                Direction.LEFT -> Pair(pos.first - 1, pos.second)
            }
        }

        fun color(): Int {
            return area.getOrDefault(pos, 0)
        }

        fun paint(color: Int): Unit {
            area[pos] = color
        }

        fun printArea() {
            val topLeft = Pair(area.keys.map { it.first }.min()!!, area.keys.map { it.second }.min()!!)
            val botRite = Pair(area.keys.map { it.first }.max()!!, area.keys.map { it.second }.max()!!)
            for (y in topLeft.second..botRite.second) {
                for (x in topLeft.first..botRite.first) {
                    System.out.print(if (area[Pair(x, y)] == 1) '#' else ' ')
                }
                System.out.println()

            }
        }
    }
}
