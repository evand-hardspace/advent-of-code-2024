package day2

import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.absoluteValue

fun main() = Path("input/input_day2.txt")
    .readLines()
    .map { line ->
        line.split(' ').map { it.toInt() }.let {
            (it.zipWithNext().all { (a, b) -> b > a } || it.zipWithNext().all { (a, b) -> b < a })
                    && it.zipWithNext().all { (a, b) -> (b - a).absoluteValue in 1..3 }
        }
    }
    .count { it }
    .let { println(it) }
