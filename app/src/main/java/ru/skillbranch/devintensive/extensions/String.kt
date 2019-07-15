package ru.skillbranch.devintensive.extensions

fun String.truncate(length: Int = 16):String{
    var str = this
    if (str.length > length) {
        str = str.trim()
    }
    str = str.take(length)
    if (this.length > length) str = "${str.trim()}..."
    return str
}

fun String.stripHtml():String{
    var str = this
    str = str.replace(Regex("<[^>]*>"), "").replace(Regex("&[a-z0-9#]+;"),"").replace(Regex("\\s+"), " ")
    return str
}