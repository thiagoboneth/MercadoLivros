package com.mercadolivre.mercadolivros.repository

import com.mercadolivre.mercadolivros.enums.BookStatus
import com.mercadolivre.mercadolivros.model.BookModel
import com.mercadolivre.mercadolivros.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface BookRepository: CrudRepository<BookModel, Int>{
    fun findByStatus(ativo: BookStatus): List<BookModel>
    fun findByCustomer(customer: CustomerModel): List<BookModel>
}
