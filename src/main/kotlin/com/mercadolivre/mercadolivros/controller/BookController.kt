package com.mercadolivre.mercadolivros.controller

import com.mercadolivre.mercadolivros.controller.request.PostBookRequest
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
        val customer = customerService.getById(request.customerId)
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

}
