package com.exercice.methodotest.domain.usecase

import com.exercice.methodotest.domain.model.Book
import com.exercice.methodotest.domain.port.BookRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import io.mockk.verify
import io.mockk.every

class BookManagerTest : StringSpec({
    val repository = mockk<BookRepository>(relaxed = true)
    val bookManager = BookManager(repository)

    "should add a book with valid title and author" {
        val book = Book("1984", "George Orwell")
        bookManager.addBook(book.title, book.author)

        verify { repository.saveBook(book) }
    }

    "should throw an exception when adding a book with empty title or author" {
        shouldThrow<IllegalArgumentException> {
            bookManager.addBook("", "Valid Author")
        }
        shouldThrow<IllegalArgumentException> {
            bookManager.addBook("Valid Title", "")
        }
        shouldThrow<IllegalArgumentException> {
            bookManager.addBook("", "")
        }
    }

    "should list books sorted by title in alphabetical order" {
        val books = listOf(
            Book("The Hobbit", "J.R.R. Tolkien"),
            Book("1984", "George Orwell"),
            Book("Animal Farm", "George Orwell")
        )
        every { repository.listBooks() } returns books

        val sortedBooks = bookManager.getAllBooksSorted()

        sortedBooks shouldBe books.sortedBy { it.title }
    }
})
