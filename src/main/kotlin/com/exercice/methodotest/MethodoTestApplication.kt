package com.exercice.methodotest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MethodoTestApplication

fun main(args: Array<String>) {
    runApplication<MethodoTestApplication>(*args)
}

fun cypher(char: Char, key: Int): Char {
    require(char in 'A'..'Z') { "Input character must be an uppercase letter" }
    require(key >= 0) { "Key must be non-negative" }

    val normalizedKey = key % 26
    val shifted = char + normalizedKey

    return if (shifted > 'Z') {
        'A' + (shifted - 'Z' - 1)
    } else {
        shifted
    }
}


