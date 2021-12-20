package com.mercadolivre.mercadolivros.extension

import com.mercadolivre.mercadolivros.controller.request.PostCustomerRequest
import com.mercadolivre.mercadolivros.controller.request.PutCustomerRequest
import com.mercadolivre.mercadolivros.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email)
}
fun PutCustomerRequest.toCustomerModel(id: String): CustomerModel {
    return CustomerModel(id = id, name = this.name, email = this.email)
}
