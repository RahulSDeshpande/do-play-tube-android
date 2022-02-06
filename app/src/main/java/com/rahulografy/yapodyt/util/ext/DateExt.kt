package com.rahulografy.yapodyt.util.ext

import java.util.*
import java.util.regex.Pattern

/**
 * Converts time in ISO 8601 to HH:MM:SS
 */
fun String.duration(): String {

    val pattern = Pattern.compile("PT(?:(\\d+)H)?(?:(\\d+)M)?(?:(\\d+)S)?")
    val matcher = pattern.matcher(this)

    var hour: Long = 0
    var min: Long = 0
    var sec: Long = 0

    if (matcher.find()) {
        if (matcher.group(1) != null) hour = matcher.group(1).toLong()
        if (matcher.group(2) != null) min = matcher.group(2).toLong()
        if (matcher.group(3) != null) sec = matcher.group(3).toLong()
    }

    var finalTime = ""

    if (hour > 0L)
        finalTime += "${String.format("%02d", hour)}:"

    finalTime +=
        if (min == 0L) "00:"
        else "${String.format("%02d", min)}:"

    finalTime +=
        if (sec == 0L) "00"
        else String.format("%02d", sec)

    println(finalTime)

    return finalTime
}

fun getDurationString(duration: Long): String {
    val output: String
    val days = duration / (24 * 60 * 60L) /* greater than a day */
    val hours = duration % (24 * 60 * 60L) / (60 * 60L) /* greater than an hour */
    val minutes = duration % (24 * 60 * 60L) % (60 * 60L) / 60L
    val seconds = duration % 60L
    output =
        when {
            duration < 0 -> {
                "0:00"
            }
            days > 0 -> {
                String.format(
                    Locale.US,
                    "%d:%02d:%02d:%02d",
                    days,
                    hours,
                    minutes,
                    seconds
                )
            }
            hours > 0 -> {
                String.format(Locale.US, "%d:%02d:%02d", hours, minutes, seconds)
            }
            else -> {
                String.format(Locale.US, "%d:%02d", minutes, seconds)
            }
        }
    return output
}
