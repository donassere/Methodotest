package com.exercice.methodotest.domain.usecase

import com.exercice.methodotest.domain.model.Book
import com.exercice.methodotest.domain.port.BookRepository

class BookManager(private val repository: BookRepository) {

    fun addBook(title: String, author: String) {
        if (title.isBlank() || author.isBlank()) {
            throw IllegalArgumentException("Both title and author must be non-empty")
        }
        val book = Book(title, author)
        repository.saveBook(book)
    }

    fun getAllBooksSorted(): List<Book> {
        return repository.listBooks().sortedBy { it.title }
    }
}
