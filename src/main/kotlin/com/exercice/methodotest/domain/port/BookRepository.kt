package com.exercice.methodotest.domain.port

import com.exercice.methodotest.domain.model.Book

interface BookRepository {
    fun saveBook(book: Book)
    fun listBooks(): List<Book>
}
