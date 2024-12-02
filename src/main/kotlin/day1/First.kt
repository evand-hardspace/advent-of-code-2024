package day1

import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.absoluteValue

fun main() = Path("input/input_day1.txt")
    .readLines()
    .map { it.substringBefore(" ").toInt() to it.substringAfterLast(" ").toInt() }
    .unzip()
    .let { (l, r) -> l.sorted().zip(r.sorted()) }
    .sumOf { (l, r) -> (r - l).absoluteValue }
    .let { println(it) }
