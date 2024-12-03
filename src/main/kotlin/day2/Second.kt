package day2

import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.absoluteValue

fun main() {
    val isSafe: List<Int>.() -> Boolean = {
        val isIncreasing = zipWithNext().all { (a, b) -> b > a }
        val isDecreasing = zipWithNext().all { (a, b) -> b < a }
        val validDifferences = zipWithNext().all { (a, b) -> (b - a).absoluteValue in 1..3 }
        (isIncreasing || isDecreasing) && validDifferences
    }

    Path("input/input_day2.txt").readLines().map { line ->
        val nums = line.split(' ').map { it.toInt() }
        if (nums.isSafe()) return@map true

        for (i in nums.indices) {
            if (nums.toMutableList().apply { removeAt(i) }.isSafe())
                return@map true
        }

        return@map false
    }
        .count { it }
        .let { println(it) }
}