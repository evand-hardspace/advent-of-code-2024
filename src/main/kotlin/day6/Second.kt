package day6

import kotlin.io.path.Path
import kotlin.io.path.readText

data class Cell(
    var visitedTimes: Int,
    var value: Char,
)

infix fun Int.coord(column: Int) = Position(this, column)

data class Position(
    val row: Int,
    val column: Int,
)

// contains infinite loop
fun main() {
    val input = Path("input/input_day6.txt").readText()

//    val input = """
//        ....#.....
//        .........#
//        ..........
//        ..#.......
//        .......#..
//        ..........
//        .#..^.....
//        ........#.
//        #.........
//        ......#...
//    """.trimIndent()

    val field = input.lines().map {
        it.toCharArray()
            .map { char -> Cell(visitedTimes = 0, char) }.toMutableList()
    }.toMutableList()

    var guardIndexNullable: Position? = null

    // find guard
    field.forEachIndexed { row, line ->
        line.forEachIndexed { column, char ->
            if (char.value == '^') guardIndexNullable = row coord column
        }
    }
    field[guardIndexNullable!!.row][guardIndexNullable!!.column] =
        field[guardIndexNullable!!]?.copy(value = '.')!!


    var loopedMazeCounter = 0
    val candidatesPosition = mutableListOf<Position>()

    for (row in 0 until field.size) {
        for (column in 0 until field[0].size) {
            if (row == guardIndexNullable!!.row && column == guardIndexNullable!!.column) continue
            if (field[row][column].value != '.') continue
            val fieldCopy = field.map { it.map { cell -> cell.copy() }.toMutableList() }.toMutableList()
                .also { it[row][column] = Cell(visitedTimes = 0, '#') }
            if (containLoop(guardIndexNullable!!, fieldCopy)) {
                candidatesPosition += row coord column
                loopedMazeCounter++
            }
        }
    }

    println(field.joinToString("\n") { it.map { cell -> cell.value }.joinToString("") })
    println(field.flatten().count { it.value == 'X' })
    println(loopedMazeCounter)
    candidatesPosition.forEach { println(it) }
}

private var x = 0
private fun containLoop(
    initialGuardIndex: Position,
    field: MutableList<MutableList<Cell>>,
): Boolean {
    println("contains loop ${x++}")
    var isLoop = false
    var direction = Direction.UP
    var guardIndex = initialGuardIndex
    while (guardIndex.isNotOnMapBorders(field)) {
        when (direction) {
            Direction.UP -> {
                if (field.getNeighbour(guardIndex, Direction.UP)?.value == '#') {
                    direction = Direction.RIGHT
                    if (field[guardIndex]?.visitedTimes!! > 1) {
                        isLoop = true
                        break
                    }
                    field[guardIndex]!!.visitedTimes++
                    continue
                }
                if (field.getNeighbour(guardIndex, Direction.UP)?.value.let { it == '.' || it == 'X' }) {
                    guardIndex = guardIndex.row - 1 coord guardIndex.column
                    field[guardIndex.row][guardIndex.column].value = 'X'
                    continue
                }
            }

            Direction.DOWN -> {
                if (field.getNeighbour(guardIndex, Direction.DOWN)?.value == '#') {
                    direction = Direction.LEFT
                    if (field[guardIndex]?.visitedTimes!! > 1) {
                        isLoop = true
                        break
                    }
                    field[guardIndex]!!.visitedTimes++
                    continue
                }
                if (field.getNeighbour(guardIndex, Direction.DOWN)?.value.let { it == '.' || it == 'X' }) {
                    guardIndex = guardIndex.row + 1 coord guardIndex.column
                    field[guardIndex.row][guardIndex.column].value = 'X'
                    continue
                }
            }

            Direction.LEFT -> {
                if (field.getNeighbour(guardIndex, Direction.LEFT)?.value == '#') {
                    direction = Direction.UP
                    if (field[guardIndex]?.visitedTimes!! > 1) {
                        isLoop = true
                        break
                    }
                    field[guardIndex]!!.visitedTimes++
                    continue
                }
                if (field.getNeighbour(guardIndex, Direction.LEFT)?.value.let { it == '.' || it == 'X' }) {
                    guardIndex = guardIndex.row coord guardIndex.column - 1
                    field[guardIndex.row][guardIndex.column].value = 'X'
                    continue
                }
            }

            Direction.RIGHT -> {
                if (field.getNeighbour(guardIndex, Direction.RIGHT)?.value == '#') {
                    direction = Direction.DOWN
                    if (field[guardIndex]?.visitedTimes!! > 1) {
                        isLoop = true
                        break
                    }
                    field[guardIndex]!!.visitedTimes++
                    continue
                }
                if (field.getNeighbour(guardIndex, Direction.RIGHT)?.value.let { it == '.' || it == 'X' }) {
                    guardIndex = guardIndex.row coord guardIndex.column + 1
                    field[guardIndex.row][guardIndex.column].value = 'X'
                    continue
                }
            }
        }
    }
    return isLoop
}

private fun Position.isNotOnMapBorders(field: List<List<Cell>>): Boolean {
    return row in 1..<field.size - 1 && column in 1..<field[0].size - 1
}

private fun MutableList<MutableList<Cell>>.getNeighbour(position: Position, direction: Direction): Cell? =
    when (direction) {
        Direction.UP -> getOrNull(position.row - 1)?.getOrNull(position.column)
        Direction.DOWN -> getOrNull(position.row + 1)?.getOrNull(position.column)
        Direction.LEFT -> getOrNull(position.row)?.getOrNull(position.column - 1)
        Direction.RIGHT -> getOrNull(position.row)?.getOrNull(position.column + 1)
    }

private operator fun MutableList<MutableList<Cell>>.get(position: Position): Cell? =
    getOrNull(position.row)?.getOrNull(position.column)
