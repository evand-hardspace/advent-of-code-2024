package day1

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() = Path("input/input_day1.txt")
    .readLines()
    .map { it.substringBefore(" ").toInt() to it.substringAfterLast(" ").toInt() }
    .unzip()
    .let { (l, r) ->
        l.map { i ->
            i * r.count { it == i }
        }
    }
    .sum()
    .let { println(it) }
