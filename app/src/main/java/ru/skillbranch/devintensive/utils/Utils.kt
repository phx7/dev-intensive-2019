package ru.skillbranch.devintensive.utils

object Utils {
    val letters = mapOf(
        "а" to  "a",
        "б" to "b",
        "в" to "v",
        "г" to "g",
        "д" to "d",
        "е" to "e",
        "ё" to "e",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "i",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sh'",
        "ъ" to "",
        "ы" to "i",
        "ь" to "",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya"
    )
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val name: List<String>? = fullName?.split(" ")?.filterNot { it == "" }
        return Pair(name?.getOrNull(0), name?.getOrNull(1))
    }

    fun toInitials(firstName:String?, lastName:String?):String{
        var result = "${firstName?.getOrNull(index = 0)?:""}${lastName?.getOrNull(0)?:""}"
        if (result == "") result = null.toString()
        return result.toUpperCase()
    }

    fun transliteration(payload:String, divider:String = " "):String{
        var result = payload
        letters.forEach{
            result = result
                .replace(it.key, it.value)
                .replace(it.key.toUpperCase(), it.value.toUpperCase())
                .replace(" ", divider)
        }
        return result
    }
}