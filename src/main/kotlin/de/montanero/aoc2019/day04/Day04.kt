package de.montanero.aoc2019.day04

class Day04 {


    fun run(): Int {
        var count = 0
        for (i in 236491..713787) {
            val i = i.toString()
            if (digitsDecrease(i) && hasDouble(i))
                count++
        }
        return count;
    }

    fun runb(): Int {
        var count = 0
        for (i in 236491..713787) {
            val i = i.toString()
            if (digitsDecrease(i) && hasExactDouble(i))
                count++
        }
        return count;
    }

    private fun hasDouble(i: String): Boolean {
        for (j  in 0..i.length-2){
            if (i[j]==i[j+1])
                return true
        }
        return false

    }

    private fun hasExactDouble(s: String): Boolean {
        var i = 0
        while (i < s.length)
        {
            val l = sequence (s,i)
            if (l == 2)
                return true
            i += l
        }
return false
    }

    private fun sequence(s: String, i: Int): Int {
        var count = 1
        for (j in i+1..s.length-1) {
            if (s[j] != s[i])
                break
            count++
        }
        return count
    }

    private fun digitsDecrease(i: String): Boolean {
        for (j  in 0..i.length-2){
            if (i[j]>i[j+1])
                return false
        }
        return true
    }
}
