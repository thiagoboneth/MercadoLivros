package com.mercadolivre.mercadolivros.repository

import com.mercadolivre.mercadolivros.controller.request.PostBookRequest
import com.mercadolivre.mercadolivros.enums.BookStatus
import com.mercadolivre.mercadolivros.model.BookModel
import org.springframework.data.repository.CrudRepository

interface BookRepository: CrudRepository<BookModel, Int>{
    fun findByName(name: PostBookRequest): List<BookModel>
    fun findByStatus(ativo: BookStatus): List<BookModel>
}
