package com.exercice.methodotest

import io.kotest.core.spec.style.StringSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.char
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll
import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldThrow

class CypherPropertyTest : StringSpec({
    "cypher should always return an uppercase letter" {
        checkAll(Arb.char(range = 'A'..'Z'), Arb.int(min = 0)) { char, key ->
            val result = cypher(char, key)
            result.isUpperCase() shouldBe true
        }
    }

    "cypher should wrap around from Z to A correctly" {
        checkAll(Arb.int(min = 0)) { key ->
            val result = cypher('Z', key)
            result.isUpperCase() shouldBe true
        }
    }

    "cypher should handle keys greater than 26 using modulo 26" {
        checkAll(Arb.char(range = 'A'..'Z'), Arb.int(min = 0)) { char, key ->
            val normalizedKey = key % 26
            val result = cypher(char, key)
            val expected = cypher(char, normalizedKey)
            result shouldBe expected
        }
    }

    "cypher should throw an error when key is negative" {
        checkAll(Arb.char(range = 'A'..'Z')) { char ->
            shouldThrow<IllegalArgumentException> {
                cypher(char, -1)
            }
        }
    }

    "cypher should throw an error when input is not an uppercase letter" {
        checkAll(Arb.char()) { char ->
            if (char !in 'A'..'Z') {
                shouldThrow<IllegalArgumentException> {
                    cypher(char, 2)
                }
            } else {
                val result = cypher(char, 2)
                result.isUpperCase() shouldBe true
            }
        }
    }
})

