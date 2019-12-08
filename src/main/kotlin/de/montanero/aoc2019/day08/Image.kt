package de.montanero.aoc2019.day08

class Image(val width: Int, val height: Int, data: String) {
    val layers: List<List<Int>> = readLayers(data)

    private fun readLayers(data: String): List<List<Int>> {
        val ret = mutableListOf<List<Int>>()

        for (lidx in 0..data.length - 1 step width * height) {
            val lData = data.substring(lidx, (lidx + width * height))
            ret.add(lData.map { it - '0' }.toList())
        }
        return ret
    }

    fun render(): List<String> {
        val result = mutableListOf<String>()
        for (line in 0..height - 1) {
            val lineStr = StringBuilder()
            for (column in 0..width - 1) {
                val idx = line * width + column
                // inverse for improver readability
                lineStr.append(when (colorAt(idx)) {
                    0 -> ' '
                    1 -> '#'
                    else -> ' '
                })
            }
            result.add(lineStr.toString())
        }
        return result
    }

    private fun colorAt(idx: Int): Int {
        layers.forEach {
            val c = it[idx]
            if (c != 2)
                return c;
        }
        return 2
    }


}

