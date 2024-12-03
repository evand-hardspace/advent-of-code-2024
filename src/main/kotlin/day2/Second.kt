package day2

import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.absoluteValue

fun main() {
    val input = Path("input/input_day2.txt").readLines()

    fun isSafe(nums: List<Int>): Boolean {
        val isIncreasing = nums.zipWithNext().all { (a, b) -> b > a }
        val isDecreasing = nums.zipWithNext().all { (a, b) -> b < a }
        val validDifferences = nums.zipWithNext().all { (a, b) -> (b - a).absoluteValue in 1..3 }

        return (isIncreasing || isDecreasing) && validDifferences
    }

    input.map { line ->
        val nums = line.split(' ').map { it.toInt() }
        if (isSafe(nums)) return@map true

        for (i in nums.indices) {
            val candidate = nums.toMutableList().apply { removeAt(i) }
            if (isSafe(candidate)) return@map true

        }
        return@map false
    }
        .count { it }
        .let { println(it) }
}