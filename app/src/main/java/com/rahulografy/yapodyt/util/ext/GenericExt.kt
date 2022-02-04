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
