package ru.skillbranch.devintensive.extensions

import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

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
    return when (val diff = (date.time - this.time) / 1000) {
        in 0..1 -> "только что"
        in 1..45 -> "несколько секунд назад"
        in 45..75 -> "минуту назад"
        in 75..45 * 60 -> "${diff / 60} минут назад"
        in 45 * 60..75 * 60 -> "час назад"
        in 75 * 60..22 * 3600 -> "${diff / 3600} часов назад"
        in 22 * 3600..26 * 3600 -> "день назад"
        in 26 * 3600..360 * 24 * 3600 -> "${diff / (24 * 3600)} дней назад"
        else -> "более года назад"
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        var str = ""
        val time_single: Int = value % 10
        val time_double: Int = value % 100
        when (this) {
            SECOND -> {
                str = when (time_double) {
                    in 10..19 -> "секунд"
                    else -> when (time_single) {
                        0, 5, 6, 7, 8, 9 -> "секунд"
                        1 -> "секунду"
                        in 2..4 -> "секунды"
                        else -> "секунд"
                    }
                }
            }
            MINUTE -> {
                str = when (time_double) {
                    in 10..19 -> "минут"
                    else -> when (time_single) {
                        0, 5, 6, 7, 8, 9 -> "минут"
                        1 -> "минуту"
                        in 2..4 -> "минуты"
                        else -> "минут"
                    }
                }
            }
            HOUR -> {
                str = when (time_double) {
                    in 10..19 -> "часов"
                    else -> when (time_single) {
                        0, 5, 6, 7, 8, 9 -> "часов"
                        1 -> "час"
                        in 2..4 -> "часа"
                        else -> "часов"
                    }
                }

            }
            DAY -> {
                str = when (time_double) {
                    in 10..19 -> "дней"
                    else -> when (time_single) {
                        0, 5, 6, 7, 8, 9 -> "дней"
                        1 -> "день"
                        in 2..4 -> "дня"
                        else -> "дней"
                    }
                }
            }
        }
        return "$value $str"
    }
}