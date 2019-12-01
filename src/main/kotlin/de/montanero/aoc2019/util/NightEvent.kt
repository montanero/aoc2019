package de.montanero.aoc2019.util

import java.time.LocalDateTime

class NightEvent(val time: LocalDateTime, val event: Event, val id: Int?) {
    enum class Event {
        START, FALLASLEEP, WAKEUP
    }
}

