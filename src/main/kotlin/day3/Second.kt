package day3

import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("input/input_day3.txt").readText()
    val tokens = input.tokenize()

    val actions = mutableListOf<ActionRepresentation>()

    var i = 0
    while (i < tokens.size) {
        when {
            tokens[i] is Token.Mul
                    && tokens.getOrNull(i + 1) is Token.LeftBrace
                    && tokens.getOrNull(i + 2) is Token.Number
                    && tokens.getOrNull(i + 3) is Token.Coma
                    && tokens.getOrNull(i + 4) is Token.Number
                    && tokens.getOrNull(i + 5) is Token.RightBrace -> {
                val result =
                    (tokens[i + 2].let { it as Token.Number }.value * tokens[i + 4].let { it as Token.Number }.value)
                actions += ActionRepresentation.Value(result)
                i += 5
            }

            tokens[i] == Token.Do -> actions += ActionRepresentation.Do
            tokens[i] == Token.Dont -> actions += ActionRepresentation.Dont

        }
        i++
    }

    var result = 0
    var skipCalculation = false

    i = -1
    while (i < actions.size - 1) {
        i++
        if (actions[i] == ActionRepresentation.Dont) {
            skipCalculation = true
            continue
        }
        if (actions[i] == ActionRepresentation.Do) {
            skipCalculation = false
            continue
        }

        if (skipCalculation.not() && actions[i] is ActionRepresentation.Value) {
            result += actions[i].let { it as ActionRepresentation.Value }.value
        }
    }

    println(result)
}


private sealed interface ActionRepresentation {
    data class Value(val value: Int) : ActionRepresentation
    data object Do : ActionRepresentation
    data object Dont : ActionRepresentation
}