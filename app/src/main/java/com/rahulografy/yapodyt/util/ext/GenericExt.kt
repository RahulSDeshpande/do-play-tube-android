package com.rahulografy.yapodyt.util.ext

/**
 * Clears existing list & newly adds specified list
 */
fun <T> ArrayList<T>.replace(newList: List<T>) {
    clear()
    addAll(newList)
}

/**
 * Kotlin extension to get the class name.
 *
 * This can also be used a TAGs for logging.
 *
 * @return Caller class' name
 */
val Any.TAG: String get() = javaClass.simpleName

/**
 * Converts any collection to an instance of [ArrayList]
 */
fun <E> Collection<E>?.toArrayList(): ArrayList<E> =
    if (this == null) arrayListOf()
    else ArrayList(this)

fun CharSequence?.isNotNullOrBlank() = this.isNullOrBlank().not()

fun <E> Collection<E>?.isNotNullOrEmpty() = this.isNullOrEmpty().not()

// ISO 8601
// fun convertTime() {
//     val downTime = 755L
//     val duration: Duration = Duration.parse("PT1M10S") // PT12M35S
//
//     // Default format
//     System.out.println(duration)
//
//     // Custom format
//     // ####################################Java-8####################################
//     var formattedElapsedTime =
//         String.format(
//             "%02d:%02d:%02d",
//             duration.toHours() % 24,
//             duration.toMinutes() % 60,
//             duration.toSeconds() % 60
//         )
//     println(formattedElapsedTime)
//     // ##############################################################################
//
//     // ####################################Java-9####################################
//     formattedElapsedTime =
//         String.format(
//             "%02d:%02d:%02d",
//             duration.toHoursPart(),
//             duration.toMinutesPart(),
//             duration.toSecondsPart()
//         )
//     println(formattedElapsedTime)
//     // ##############################################################################
// }
