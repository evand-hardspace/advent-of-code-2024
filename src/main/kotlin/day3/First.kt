package day3

import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("input/input_day3.txt").readText()
    val tokens = input.tokenize()

    var result = 0
    var i = 0
    while (i < tokens.size) {
        if (
            tokens[i] is Token.Mul
            && tokens.getOrNull(i + 1) is Token.LeftBrace
            && tokens.getOrNull(i + 2) is Token.Number
            && tokens.getOrNull(i + 3) is Token.Coma
            && tokens.getOrNull(i + 4) is Token.Number
            && tokens.getOrNull(i + 5) is Token.RightBrace
        ) {
            (tokens[i + 2].let { it as Token.Number }.value * tokens[i + 4].let { it as Token.Number }.value)
                .let { result += it }
            i += 5
        }
        i++
    }

    println(result)
}

fun String.tokenize(): List<Token> {
    var i = 0

    val result = mutableListOf<Token>()
    while (i < length) {
        when (this[i]) {
            '(' -> result += Token.LeftBrace
            ')' -> result += Token.RightBrace
            ',' -> result += Token.Coma
            'm' -> {
                if (
                    getOrNull(i + 1) == 'u'
                    && getOrNull(i + 2) == 'l'
                ) {
                    result += Token.Mul
                    i += 2
                }
            }

            in '0'..'9' -> {
                var number = this[i].toString()
                while (this.getOrNull(i + 1) in '0'..'9') {
                    number += this[i + 1]
                    i++
                }
                result += Token.Number(number.toInt())
            }

            'd' -> {
                when {
                    getOrNull(i + 1) == 'o'
                            && getOrNull(i + 2) == 'n'
                            && getOrNull(i + 3) == '\''
                            && getOrNull(i + 4) == 't'
                            && getOrNull(i + 5) == '('
                            && getOrNull(i + 6) == ')' -> {
                        result += Token.Dont
                        i += 5
                    }

                    getOrNull(i + 1) == 'o'
                            && getOrNull(i + 2) == '('
                            && getOrNull(i + 3) == ')' -> {
                        result += Token.Do
                        i += 3
                    }

                    else -> result += Token.Other

                }
            }

            else -> result += Token.Other
        }
        i++
    }
    return result
}

sealed interface Token {
    data class Number(val value: Int) : Token
    data object Mul : Token
    data object LeftBrace : Token
    data object RightBrace : Token
    data object Coma : Token
    data object Other : Token
    data object Do : Token
    data object Dont : Token
}