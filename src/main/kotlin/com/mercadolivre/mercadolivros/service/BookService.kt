package com.mercadolivre.mercadolivros.service

import com.mercadolivre.mercadolivros.enums.BookStatus
import com.mercadolivre.mercadolivros.model.BookModel
import com.mercadolivre.mercadolivros.model.CustomerModel
import com.mercadolivre.mercadolivros.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService (
    val bookRepository: BookRepository
        ){

    fun create(book: BookModel) {
        bookRepository.save(book)
    }

    fun findAll(): List<BookModel> {
        return  bookRepository.findAll().toList()
    }

    fun findActives(): List<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO)

    }

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow()
    }

    fun delete(id: Int) {
        var book = findById(id)
        book.status = BookStatus.CANCELADO
        updateById(book)
    }

    fun updateById(book: BookModel) {
        bookRepository.save(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        var books = bookRepository.findByCustomer(customer)
        for (book in books){
            book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }
}
