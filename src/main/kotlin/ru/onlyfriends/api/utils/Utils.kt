package ru.onlyfriends.api.utils

fun <T>T.isEmpty(predicate: () -> Unit): T {
    if (this == null) predicate()
    return this
}

fun <T>T.isNotEmpty(predicate: () -> Unit): T {
    if (this != null) predicate()
    return this
}


fun <T>T?.throwIsEmpty(predicate: () -> Exception): T {
    if (this == null) throw predicate()
    return this
}

fun <T>T?.throwIsNotEmpty(predicate: () -> Exception): T? {
    if (this != null) throw predicate()
    return null
}