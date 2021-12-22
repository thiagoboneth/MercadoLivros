package com.mercadolivre.mercadolivros.service

import com.mercadolivre.mercadolivros.model.CustomerModel
import com.mercadolivre.mercadolivros.repository.CustomerRepository
import org.springframework.stereotype.Service


@Service
class CustomerService (
    val customerRepository: CustomerRepository,
    val bookService: BookService
        ){

    fun getAll(name:String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByName(name)
        }
            return  customerRepository.findAll().toList()
    }

    fun findById(id:Int): CustomerModel {
        if (!customerRepository.existsById(id)){
            throw Exception("Error ID não encontrado")
        }
        return customerRepository.findById(id).orElseThrow()
    }

    fun updateCustomer(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)){
            throw Exception("Error ID não encontrado")
        }
        customerRepository.save(customer)
    }

    fun deleteCustomer(id:Int) {
        if (!customerRepository.existsById(id)){
            throw Exception("ID não encontrado")
        }
        var customer = findById(id)
        bookService.deleteByCustomer(customer)
        customerRepository.deleteById(id)
    }

    fun createCustomer(customer: CustomerModel) {
        customerRepository.save(customer)
    }

}
