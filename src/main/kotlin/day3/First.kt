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
    var currentIndex = 0

    val result = mutableListOf<Token>()
    while (currentIndex < length) {
        when (this[currentIndex]) {
            'm' -> {
                if (
                    getOrNull(currentIndex + 1) == 'u'
                    && getOrNull(currentIndex + 2) == 'l'
                ) {
                    result += Token.Mul
                    currentIndex += 2
                }
            }

            '(' -> result += Token.LeftBrace
            ')' -> result += Token.RightBrace
            ',' -> result += Token.Coma
            in '0'..'9' -> {
                var number = this[currentIndex].toString()
                while (this.getOrNull(currentIndex + 1) in '0'..'9') {
                    number += this[currentIndex + 1]
                    currentIndex++
                }
                result += Token.Number(number.toInt())
            }

            else -> result += Token.Other
        }
        currentIndex++
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
}