package com.exercice.methodotest

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldThrow

class CypherTest : StringSpec({
    "cypher with valid input should return correct character" {
        cypher('A', 2) shouldBe 'C'
    }

    "cypher should return correct character for 'A' and 5" {
        cypher('A', 5) shouldBe 'F'
    }

    "cypher should wrap around from Z to A" {
        cypher('Z', 1) shouldBe 'A'
    }

    "cypher with large key should normalize key" {
        cypher('A', 27) shouldBe 'B'
    }

    "cypher with key equal to 26 should return the same character" {
        cypher('A', 26) shouldBe 'A'
    }

    "cypher with negative key should throw IllegalArgumentException" {
        shouldThrow<IllegalArgumentException> {
            cypher('A', -1)
        }
    }

    "cypher with non-uppercase letter should throw IllegalArgumentException" {
        shouldThrow<IllegalArgumentException> {
            cypher('a', 2)
        }
    }
})
