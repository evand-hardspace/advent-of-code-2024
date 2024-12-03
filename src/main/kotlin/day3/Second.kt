package day3

import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("input/input_day3.txt").readText()
    val tokens = input.tokenize().calculateAndReduce()

    var result = 0
    var skipCalculation = false
    var i = -1
    while (i < tokens.size - 1) {
        i++
        if (tokens[i] == Token.Dont) {
            skipCalculation = true
            continue
        }
        if (tokens[i] == Token.Do) {
            skipCalculation = false
            continue
        }

        if (skipCalculation.not() && tokens[i] is Token.Number) {
            result += tokens[i].let { it as Token.Number }.value
        }
    }

    println(result)
}

private fun List<Token>.calculateAndReduce(): List<Token> {
    val tokens = mutableListOf<Token>()

    var i = 0
    while (i < size) {
        when {
            this[i] == Token.Do -> tokens += Token.Do
            this[i] == Token.Dont -> tokens += Token.Dont
            this[i] is Token.Mul
                    && getOrNull(i + 1) is Token.LeftBrace
                    && getOrNull(i + 2) is Token.Number
                    && getOrNull(i + 3) is Token.Coma
                    && getOrNull(i + 4) is Token.Number
                    && getOrNull(i + 5) is Token.RightBrace -> {
                val result = (this[i + 2] as Token.Number).value * (this[i + 4] as Token.Number).value
                tokens += Token.Number(result)
                i += 5
            }
        }
        i++
    }
    return tokens
}
