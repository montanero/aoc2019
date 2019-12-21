package de.montanero.aoc2019.day20

import de.montanero.aoc2019.day01.StringFileReader
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay20 {
    @Test
    fun test() {
        val maze = StringFileReader.read("/input20.txt")
        val labels = findLabels(maze)
        val start = labels.find { it.first == "AA" }!!.second
        val end = labels.find { it.first == "ZZ" }!!.second
        val tunnels = findTunnels(labels)

        val s0 = findShortest (start, end, maze, tunnels )
        assertEquals (42, s0)

    }

    private fun findShortest(start: TestDay20.Pos, end: TestDay20.Pos, maze: List<String>, tunnels: Map<TestDay20.Pos, TestDay20.Pos>): Int {
        val visited = mutableSetOf<Pos>()
        var turns = 0
        visited.add (start)
        var heads = listOf(start)
        while (!visited.contains(end))
        {
            turns ++
            heads = heads.map {p->
                val tries = mutableListOf (Pos(p.x-1,p.y),Pos(p.x+1,p.y),Pos(p.x,p.y-1),Pos(p.x,p.y+1))
                val tp = tunnels[p]
                if (tp != null)
                    tries.add(tp)
                tries.filter { p0 -> maze[p0.y][p0.x] == '.'  && !visited.contains(p0) }
            }.flatten()
            visited.addAll(heads)
        }
        return turns;
    }

    private fun findTunnels(labels: List<Pair<String, Pos>>): Map<Pos, Pos> =
            labels.filter { it.first != "AA" && it.first != "ZZ" }.map { it.first }.map { l ->
                val positions = labels.filter { it.first == l }.map { it.second }
                assert(positions.size == 2)
                listOf(Pair(positions[0], positions[1]), Pair(positions[1], positions[0]))
            }.flatten().toMap()


    private fun findLabels(maze: List<String>): List<Pair<String, Pos>> {
        val labels = mutableListOf<Pair<String, Pos>>()
        for (y in 0..maze.size - 2) {
            for (x in 0..maze[0].length - 2) {
                val here = maze[y][x]
                if (here.isLetter()) {
                    val hh = maze[y][x + 1]
                    if (hh.isLetter()) {
                        val label = here.toString() + hh
                        if (x > 0 && maze[y][x - 1] == '.') {
                            labels.add(Pair(label, Pos(x - 1, y)))
                        } else {
                            labels.add(Pair(label, Pos(x + 2, y)))
                        }
                    } else {
                        val hv = maze[y + 1][x]
                        if (hv.isLetter()) {
                            val label = here.toString() + hv
                            if (y > 0 && maze[y - 1][x] == '.') {
                                labels.add(Pair(label, Pos(x, y - 1)))
                            } else {
                                labels.add(Pair(label, Pos(x, y + 2)))
                            }
                        }
                    }
                }
            }
        }
        return labels
    }


    private data class Pos(val x: Int, val y: Int)
}