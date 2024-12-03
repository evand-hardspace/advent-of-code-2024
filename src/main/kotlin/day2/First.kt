package day2

import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.absoluteValue

fun main() {
    val input = Path("input/input_day2.txt").readLines()

    val safeCount = input.map { line ->
        val nums = line.split(' ').map { it.toInt() }

        val isIncreasing = nums.zipWithNext().all { (a, b) -> b > a }
        val isDecreasing = nums.zipWithNext().all { (a, b) -> b < a }
        val validDifferences = nums.zipWithNext().all { (a, b) -> (b - a).absoluteValue in 1..3 }

        (isIncreasing || isDecreasing) && validDifferences
    }.count { it }

    println(safeCount)
}

//fun main() {
//    val input = Path("input/input_day2.txt").readLines()
//
//    input.map {
//        val nums = it.split(' ').map { i -> i.toInt() }
//        var isSafe = true
//        val isIncreasing = nums.zipWithNext().all { (a, b) -> b > a }
//        val isDecreasing = nums.zipWithNext().all { (a, b) -> b < a }
//        if(isIncreasing.not() && isDecreasing.not()) return@map false
//        for (i in 0..<nums.size - 1) {
//            if ((nums[i] - nums[i + 1]).absoluteValue !in 1..3) {
//                isSafe = false
//            }
//        }
//        isSafe
//    }
//        .count { it }
//        .let { println(it) }
//}