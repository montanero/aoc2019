package de.montanero.aoc2019.day12;

 data class ThreeDee(val x: Int, val y: Int, val z: Int) {

    operator fun plus(other: ThreeDee) =
            ThreeDee(x + other.x, y + other.y, z + other.z)


    operator fun minus(other: ThreeDee) =
            ThreeDee(x - other.x, y - other.y, z - other.z)

    fun sign() =
            ThreeDee(sign(x), sign(y), sign(z))


     fun absSum(): Int =abs(x)+abs(y)+abs(z)

     private companion object {
         private fun sign(a: Int) =
                 if (a < 0) -1 else if (a > 0) 1 else 0

         private fun abs(a: Int) =
                 if (a < 0) -a else a
     }

 }
