package ru.skillbranch.devintensive.extensions

fun String.truncate(length: Int = 16):String{
    var str = this
    if (str.length > length) {
        str = str.substring(0, length)
    }
    if (str.last() == ' ') str = str.trimEnd()
    return "$str..."
}

fun String.stripHtml():String{
    var str = this
    str = str.replace(Regex("<[^>]*>"), "").replace(Regex("\\s+"), " ")
    return str
}