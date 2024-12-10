package com.exercice.methodotest.domain.usecase

import com.exercice.methodotest.domain.model.Book
import com.exercice.methodotest.domain.port.BookRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll
import io.mockk.every
import io.mockk.mockk

class BookManagerPropertyTest : StringSpec({
    val repository = mockk<BookRepository>(relaxed = true)
    val bookManager = BookManager(repository)

    "the returned book list contains all stored books" {
        checkAll(
            Arb.list(Arb.string(minSize = 1), 1..10),
            Arb.list(Arb.string(minSize = 1), 1..10)
        ) { titles, authors ->
            val books = titles.zip(authors) { title, author -> Book(title, author) }

            every { repository.listBooks() } returns books

            val returnedBooks = bookManager.getAllBooksSorted()
            returnedBooks.toSet() shouldBe books.toSet()
        }
    }

    "books are returned sorted by title alphabetically" {
        checkAll(
            Arb.list(Arb.string(minSize = 1), 1..10),
            Arb.string(minSize = 1)
        ) { titles, author ->
            val books = titles.map { title -> Book(title, author) }

            every { repository.listBooks() } returns books

            val sortedBooks = bookManager.getAllBooksSorted()
            sortedBooks shouldBe books.sortedBy { it.title }
        }
    }

})
