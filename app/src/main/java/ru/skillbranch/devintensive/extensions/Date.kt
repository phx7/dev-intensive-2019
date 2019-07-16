package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format((this))
}

fun Date.add(value: Int, unit: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (unit) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    return when (val diff : Long = (date.time - this.time)) {
        in -1 * SECOND..1 * SECOND -> "только что"
        in 1 * SECOND..45 * SECOND -> "несколько секунд назад"
        in -45 * SECOND..-1 * SECOND -> "через несколько секунд"
        in 45 * SECOND..75 * SECOND -> "минуту назад"
        in -75 * SECOND..-45 * SECOND -> "через минуту"
        in 75 * SECOND..45 * MINUTE -> "${TimeUnits.MINUTE.plural(diff / MINUTE)} назад"
        in -45 * MINUTE .. -75 * SECOND -> "через ${TimeUnits.MINUTE.plural(abs(diff) / MINUTE)}"
        in 45 * MINUTE..75 * MINUTE -> "час назад"
        in -75 * MINUTE .. -45 * MINUTE -> "через час"
        in 75 * MINUTE..22 * HOUR -> "${TimeUnits.HOUR.plural(diff / HOUR)} назад"
        in -22 * HOUR..-75 * MINUTE -> "через ${TimeUnits.HOUR.plural(abs(diff) / HOUR)}"
        in 22 * HOUR..26 * HOUR -> "день назад"
        in -26 * HOUR..-22 * HOUR -> "через день"
        in 26 * HOUR..360 * DAY -> "${TimeUnits.DAY.plural(diff / DAY)} назад"
        in -360 * DAY..-26 * HOUR -> "через ${TimeUnits.DAY.plural(abs(diff) / DAY)}"
        in 360 * DAY .. Long.MAX_VALUE -> "более года назад"
        else -> "более чем через год"
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Long): String {
        var str = ""
        val time_single: Long = value % 10
        val time_double: Long = value % 100
        when (this) {
            SECOND -> {
                str = when (time_double) {
                    in 10..19 -> "секунд"
                    else -> when (time_single) {
                        0L, 5L, 6L, 7L, 8L, 9L -> "секунд"
                        1L -> "секунду"
                        in 2..4 -> "секунды"
                        else -> "секунд"
                    }
                }
            }
            MINUTE -> {
                str = when (time_double) {
                    in 10..19 -> "минут"
                    else -> when (time_single) {
                        0L, 5L, 6L, 7L, 8L, 9L -> "минут"
                        1L -> "минуту"
                        in 2..4 -> "минуты"
                        else -> "минут"
                    }
                }
            }
            HOUR -> {
                str = when (time_double) {
                    in 10..19 -> "часов"
                    else -> when (time_single) {
                        0L, 5L, 6L, 7L, 8L, 9L -> "часов"
                        1L -> "час"
                        in 2..4 -> "часа"
                        else -> "часов"
                    }
                }

            }
            DAY -> {
                str = when (time_double) {
                    in 10..19 -> "дней"
                    else -> when (time_single) {
                        0L, 5L, 6L, 7L, 8L, 9L -> "дней"
                        1L -> "день"
                        in 2..4 -> "дня"
                        else -> "дней"
                    }
                }
            }
        }
        return "$value $str"
    }
}