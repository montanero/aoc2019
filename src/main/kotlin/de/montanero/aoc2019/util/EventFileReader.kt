package de.montanero.aoc2019.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EventFileReader(resource: String) {

    private val regexBegins = """\[(.*)] Guard #(\d+) begins shift""".toRegex()
    private val regexAsleep = """\[(.*)] falls asleep""".toRegex()
    private val regexWakeup = """\[(.*)] wakes up""".toRegex()

    val list: List<NightEvent>

    init {
        val reader = EventFileReader::class.java.getResourceAsStream(resource).reader(Charsets.UTF_8)
        val l = mutableListOf<NightEvent>()
        reader.use {
            it.forEachLine { line ->
                val m1 = regexBegins.matchEntire(line)
                if (m1 != null) {
                    val (dateString, id) = m1.destructured
                    l.add(NightEvent(parse(dateString), NightEvent.Event.START, id.toInt()))
                } else {
                    val m2 = regexAsleep.matchEntire(line)
                    if (m2 != null) {
                        val dateString = m2.groupValues[1]
                        l.add(NightEvent(parse(dateString), NightEvent.Event.FALLASLEEP, -1))
                    } else {
                        val m3 = regexWakeup.matchEntire(line)
                        if (m3 != null) {
                            val dateString = m3.groupValues[1]
                            l.add(NightEvent(parse(dateString), NightEvent.Event.WAKEUP, -1))
                        } else
                            throw RuntimeException("illegal format")
                    }
                }
            }
        }
        list = l.toList()
    }

    private fun parse(s: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        return LocalDateTime.parse(s, formatter)
    }
}