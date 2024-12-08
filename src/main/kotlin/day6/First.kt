package day6

import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("input/input_day6.txt").readText()
    val field = input.lines().map {
        it.toCharArray().toMutableList()
    }.toMutableList()

    var guardIndexNullable: Pair<Int, Int>? = null

    // find guard
    field.forEachIndexed { row, line ->
        line.forEachIndexed { column, char ->
            if (char == '^') guardIndexNullable = row to column
        }
    }
    var guardIndex = requireNotNull(guardIndexNullable) { "No guard found" }
    field[guardIndex.first][guardIndex.second] = 'X'

    var direction = Direction.UP
    while(guardIndex.isNotOnMapBorders(field)) {
        when(direction) {
            Direction.UP -> {
                if(field.getOrNull(guardIndex.first - 1)?.getOrNull(guardIndex.second) == '#') {
                    direction = Direction.RIGHT
                    continue
                }
                if(field.getOrNull(guardIndex.first - 1)?.getOrNull(guardIndex.second).let { it == '.' || it == 'X'}) {
                    guardIndex = guardIndex.first - 1 to guardIndex.second
                    field[guardIndex.first][guardIndex.second] = 'X'
                    continue
                }
            }
            Direction.DOWN -> {
                if(field.getOrNull(guardIndex.first + 1)?.getOrNull(guardIndex.second) == '#') {
                    direction = Direction.LEFT
                    continue
                }
                if(field.getOrNull(guardIndex.first + 1)?.getOrNull(guardIndex.second).let { it == '.' || it == 'X'}) {
                    guardIndex = guardIndex.first + 1 to guardIndex.second
                    field[guardIndex.first][guardIndex.second] = 'X'
                    continue
                }
            }
            Direction.LEFT -> {
                if(field.getOrNull(guardIndex.first)?.getOrNull(guardIndex.second - 1) == '#') {
                    direction = Direction.UP
                    continue
                }
                if(field.getOrNull(guardIndex.first)?.getOrNull(guardIndex.second - 1).let { it == '.' || it == 'X'}) {
                    guardIndex = guardIndex.first to guardIndex.second - 1
                    field[guardIndex.first][guardIndex.second] = 'X'
                    continue
                }
            }
            Direction.RIGHT -> {
                if(field.getOrNull(guardIndex.first)?.getOrNull(guardIndex.second + 1) == '#') {
                    direction = Direction.DOWN
                    continue
                }
                if(field.getOrNull(guardIndex.first)?.getOrNull(guardIndex.second + 1).let { it == '.' || it == 'X'}) {
                    guardIndex = guardIndex.first to guardIndex.second + 1
                    field[guardIndex.first][guardIndex.second] = 'X'
                    continue
                }
            }
        }
    }

    println(guardIndex)
    println(field.joinToString("\n") { it.joinToString("") })
    println(field.flatten().count { it == 'X' })
}

private fun Pair<Int, Int>.isNotOnMapBorders(field: List<List<Char>>): Boolean {
    return first in 1..<field.size - 1 && second in 1..<field[0].size - 1
}

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}
