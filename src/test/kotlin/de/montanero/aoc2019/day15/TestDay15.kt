package de.montanero.aoc2019.day15

import de.montanero.aoc2019.intcode.IntcodeFile
import de.montanero.aoc2019.intcode.IntcodeMachine
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay15 {


    @Test
    fun test() {
        val maze = explore();
        maze.printArea()
        val s = findShortest(maze)
        assertEquals (214, s)
    }

    fun explore(): Maze {
        var maze = Maze()
        val m = IntcodeMachine(IntcodeFile.read("/input15.txt"))
        var now = Pos(0, 0)
        var undoes = listOf<Direction>()
        maze.draw(now, Maze.RoomType.ROOM)
        while (true) {
            val directions = maze.getUnexploredDirections(now)
            if (directions.isEmpty()) {
                if (undoes.isEmpty())
                    return maze
                val undoDirection = undoes.last()
                undoes = undoes.dropLast(1)
                now = now.move(undoDirection)
                m.input.add(undoDirection.code.toLong())
                m.runToOutput()

            } else {
                val dir = directions.first()
                m.input.add(dir.code.toLong())
                m.runToOutput()
                val code = m.output.last().toInt()
                val rt = Maze.RoomType.values().find { it.code == code }!!
                maze.draw(now.move(dir), rt)
                if (rt == Maze.RoomType.ROOM || rt == Maze.RoomType.OXYGEN) {
                    now = now.move(dir)
                    undoes = undoes + dir.reverse()
                }
            }
        }
    }


    fun findShortest(maze: Maze): Int {
        var walked = setOf(Pos(0, 0))
        var heads = setOf(Pos(0, 0))
        var distance = 1
        while (true) {
            val newheads = heads.map { maze.getWalkablePositions(it) }.flatten().toSet() - walked
            if (newheads.any { maze.get(it) == Maze.RoomType.OXYGEN })
                return distance
            walked = walked + newheads
            heads = newheads
            distance++
        }
    }


    enum class Direction(val code: Int) {
        NORTH(1), EAST(4), SOUTH(2), WEST(3);

        fun reverse(): Direction =
                when (this) {
                    NORTH -> SOUTH
                    SOUTH -> NORTH
                    WEST -> EAST
                    EAST -> WEST
                }


    }

    data class Pos(val x: Int, val y: Int) {
        fun move(dir: Direction): Pos {
            return when (dir) {
                Direction.SOUTH -> Pos(x, y + 1)
                Direction.NORTH -> Pos(x, y - 1)
                Direction.WEST -> Pos(x - 1, y)
                Direction.EAST -> Pos(x + 1, y)
            }
        }
    }

    class Maze {
        var area = mutableMapOf<Pos, RoomType>()

        enum class RoomType(val code: Int) { ROOM(1), WALL(0), OXYGEN(2), UNEXPLORED(-1) }

        fun draw(pos: Pos, t: RoomType) {
            area[pos] = t
        }

        fun get(pos: Pos): RoomType {
            return area.getOrDefault(pos, RoomType.UNEXPLORED)
        }

        fun printArea() {
            val topLeft = Pos(area.keys.map { it.x }.min()!!, area.keys.map { it.y }.min()!!)
            val botRite = Pos(area.keys.map { it.x }.max()!!, area.keys.map { it.y }.max()!!)
            for (y in topLeft.y..botRite.y) {
                for (x in topLeft.x..botRite.x) {
                    System.out.print(when (area.getOrDefault(Pos(x, y), RoomType.UNEXPLORED)) {
                        RoomType.UNEXPLORED -> ' '
                        RoomType.WALL -> '#'
                        RoomType.ROOM -> '.'
                        RoomType.OXYGEN -> 'X'
                    })
                }
                System.out.println()

            }
        }

        fun getUnexploredDirections(pos: Pos): List<Direction> =
                Direction.values().filter { this.get(pos.move(it)) == RoomType.UNEXPLORED }.toList()

        fun getWalkablePositions(pos: TestDay15.Pos): Set<Pos> =
                Direction.values().map { pos.move(it) }.filter { this.get(it) != RoomType.WALL }.toSet()

    }

}