package day1

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() = Path("input/input_day1.txt")
    .readLines()
    .map { it.substringBefore(" ").toInt() to it.substringAfterLast(" ").toInt() }
    .unzip()
    .let { (l, r) ->
        l.map { li ->
            var score = 0
            r.forEach { ri ->
                if (ri == li) score++
            }
            li * score
        }
    }
    .sum()
    .let { println(it) }
