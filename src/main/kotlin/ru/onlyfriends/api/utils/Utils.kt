package ru.onlyfriends.api.utils

fun <T>T.isEmpty(predicate: () -> Unit) {
    if (this == null) predicate()
}

fun <T>T.isNotEmpty(predicate: () -> Unit) {
    if (this != null) predicate()
}