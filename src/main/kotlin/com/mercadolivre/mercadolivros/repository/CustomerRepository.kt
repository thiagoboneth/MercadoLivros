package com.mercadolivre.mercadolivros.repository

import com.mercadolivre.mercadolivros.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface CustomerRepository: CrudRepository<CustomerModel, Int>{
    fun findByName(name: String): List<CustomerModel>

}
