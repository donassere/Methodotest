package com.exercice.methodotest.domain.usecase

import com.exercice.methodotest.domain.model.Book
import com.exercice.methodotest.domain.port.BookRepository

class InMemoryBookRepository : BookRepository {
    private val books = mutableListOf<Book>()

    override fun saveBook(book: Book) {
        books.add(book)
    }

    override fun listBooks(): List<Book> {
        return books
    }
}
