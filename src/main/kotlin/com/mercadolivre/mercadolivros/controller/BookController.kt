package com.mercadolivre.mercadolivros.controller

import com.mercadolivre.mercadolivros.controller.request.PostBookRequest
import com.mercadolivre.mercadolivros.controller.request.PutBookRequest
import com.mercadolivre.mercadolivros.extension.toBookModel
import com.mercadolivre.mercadolivros.model.BookModel
import com.mercadolivre.mercadolivros.service.BookService
import com.mercadolivre.mercadolivros.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("book")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: PostBookRequest) {
        val customer = customerService.findById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun findAll(): List<BookModel> {
        return bookService.findAll()
    }

    @GetMapping("/active")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun findActivers(): List<BookModel> {
        return bookService.findActives()
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun findById(@PathVariable id:Int): BookModel{
        return bookService.findById(id)
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id:Int) {
        bookService.delete(id)
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateById(@PathVariable id:Int, @RequestBody book: PutBookRequest) {
        val bookSaved = bookService.findById(id)
        bookService.updateById(book.toBookModel(bookSaved))
    }
}
