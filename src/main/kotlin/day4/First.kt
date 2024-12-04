package day4

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    val matrix: List<List<Char>> =
        Path("input/input_day4.txt").readLines().map { it.toCharArray().toList() }

    var count = 0
    for (row in matrix.indices) {
        for (column in matrix[0].indices) {
            if (matrix[row][column] != 'X') continue
            // 12
            if (
                matrix.getOrNull(row - 1)?.getOrNull(column) == 'M'
                && matrix.getOrNull(row - 2)?.getOrNull(column) == 'A'
                && matrix.getOrNull(row - 3)?.getOrNull(column) == 'S'
            ) count++

            // 6
            if (
                matrix.getOrNull(row + 1)?.getOrNull(column) == 'M'
                && matrix.getOrNull(row + 2)?.getOrNull(column) == 'A'
                && matrix.getOrNull(row + 3)?.getOrNull(column) == 'S'
            ) count++

            // 3
            if (
                matrix.getOrNull(row)?.getOrNull(column + 1) == 'M'
                && matrix.getOrNull(row)?.getOrNull(column + 2) == 'A'
                && matrix.getOrNull(row)?.getOrNull(column + 3) == 'S'
            ) count++

            // 9
            if (
                matrix.getOrNull(row)?.getOrNull(column - 1) == 'M'
                && matrix.getOrNull(row)?.getOrNull(column - 2) == 'A'
                && matrix.getOrNull(row)?.getOrNull(column - 3) == 'S'
            ) count++

            // 1
            if (
                matrix.getOrNull(row - 1)?.getOrNull(column + 1) == 'M'
                && matrix.getOrNull(row - 2)?.getOrNull(column + 2) == 'A'
                && matrix.getOrNull(row - 3)?.getOrNull(column + 3) == 'S'
            ) count++

            // 4
            if (
                matrix.getOrNull(row + 1)?.getOrNull(column + 1) == 'M'
                && matrix.getOrNull(row + 2)?.getOrNull(column + 2) == 'A'
                && matrix.getOrNull(row + 3)?.getOrNull(column + 3) == 'S'
            ) count++

            // 7
            if (
                matrix.getOrNull(row + 1)?.getOrNull(column - 1) == 'M'
                && matrix.getOrNull(row + 2)?.getOrNull(column - 2) == 'A'
                && matrix.getOrNull(row + 3)?.getOrNull(column - 3) == 'S'
            ) count++

            // 10
            if (
                matrix.getOrNull(row - 1)?.getOrNull(column - 1) == 'M'
                && matrix.getOrNull(row - 2)?.getOrNull(column - 2) == 'A'
                && matrix.getOrNull(row - 3)?.getOrNull(column - 3) == 'S'
            ) count++
        }
    }
    println(count)
}