package com.mercadolivre.mercadolivros.extension

import com.mercadolivre.mercadolivros.controller.request.PostBookRequest
import com.mercadolivre.mercadolivros.controller.request.PostCustomerRequest
import com.mercadolivre.mercadolivros.controller.request.PutBookRequest
import com.mercadolivre.mercadolivros.controller.request.PutCustomerRequest
import com.mercadolivre.mercadolivros.enums.BookStatus
import com.mercadolivre.mercadolivros.model.BookModel
import com.mercadolivre.mercadolivros.model.CustomerModel
import java.util.*

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email)
}
fun PutCustomerRequest.toCustomerModel(id: Int): CustomerModel {
    return CustomerModel(id = id, name = this.name, email = this.email)
}

fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(
        name = this.name, price = this.price, status = BookStatus.ATIVO, customer = customer
    )
}

fun PutBookRequest.toBookModel(book: BookModel): BookModel {
    return BookModel(
        id = book.id, name = this.name?:book.name, price = this.price?:book.price, status = book.status, customer = book.customer
    )
}

