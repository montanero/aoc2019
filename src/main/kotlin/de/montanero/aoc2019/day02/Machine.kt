package de.montanero.aoc2019.day02

class Machine(val memory: MutableList<Int>) {

    var ip:Int = 0;
    fun run():Unit {
        while (!isStopped())
            step ();

    }

    private fun step() {
        when (memory[ip])
        {
            1 -> memory[memory[ip+3]] = memory[memory[ip+1]] + memory[memory[ip+2]]
            2 -> memory[memory[ip+3]] = memory[memory[ip+1]] * memory[memory[ip+2]]
            else -> return
        }
        ip += 4
    }

    fun isStopped(): Boolean
    {
        return memory[ip] == 99
    }
}