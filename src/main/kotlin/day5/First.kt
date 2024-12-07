package day5

import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("input/input_day5.txt").readText().removeSuffix("\n")

    val rules = input.substringBefore("\n\n").lines()
        .map {
            it.split("|").map { i -> i.toInt() }
                .let { (f, s) -> f to s }
        }

    val updates = input.substringAfter("\n\n").lines()
        .map { line -> line.split(",").map { it.toInt() } }

    updates.filter { update ->
        var violatedRule = false
        update.forEachPair { f, s ->
            if (rules.any { (fRule, sRule) -> f == sRule && s == fRule }) {
                violatedRule = true
            }
        }
        violatedRule.not()
    }
        .sumOf { it[it.size / 2] }
        .let { println(it) }
}

private inline fun <T> List<T>.forEachPair(block: (T, T) -> Unit) {
    for (i in indices) {
        for (j in i + 1..<size) {
            block(this[i], this[j])
        }
    }
}
