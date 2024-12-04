package day4

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    val matrix: List<List<Char>> =
        Path("input/input_day4.txt").readLines().map { it.toCharArray().toList() }

    var count = 0
    for (row in matrix.indices) {
        for (column in matrix[0].indices) {
            if (matrix[row][column] != 'A') continue
            // S up
            if (
                matrix.getOrNull(row - 1)?.getOrNull(column - 1) == 'S' // 10
                && matrix.getOrNull(row - 1)?.getOrNull(column + 1) == 'S' // 1
                && matrix.getOrNull(row + 1)?.getOrNull(column + 1) == 'M' // 5
                && matrix.getOrNull(row + 1)?.getOrNull(column - 1) == 'M' // 7
            ) count++

            // S right
            if (
                matrix.getOrNull(row - 1)?.getOrNull(column - 1) == 'M'
                && matrix.getOrNull(row - 1)?.getOrNull(column + 1) == 'S'
                && matrix.getOrNull(row + 1)?.getOrNull(column + 1) == 'S'
                && matrix.getOrNull(row + 1)?.getOrNull(column - 1) == 'M'
            ) count++

            // S down
            if (
                matrix.getOrNull(row - 1)?.getOrNull(column - 1) == 'M'
                && matrix.getOrNull(row - 1)?.getOrNull(column + 1) == 'M'
                && matrix.getOrNull(row + 1)?.getOrNull(column + 1) == 'S'
                && matrix.getOrNull(row + 1)?.getOrNull(column - 1) == 'S'
            ) count++

            // S left
            if (
                matrix.getOrNull(row - 1)?.getOrNull(column - 1) == 'S'
                && matrix.getOrNull(row - 1)?.getOrNull(column + 1) == 'M'
                && matrix.getOrNull(row + 1)?.getOrNull(column + 1) == 'M'
                && matrix.getOrNull(row + 1)?.getOrNull(column - 1) == 'S'
            ) count++
        }
    }
    println(count)
}