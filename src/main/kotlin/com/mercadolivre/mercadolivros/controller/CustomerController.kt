package com.mercadolivre.mercadolivros.controller

import com.mercadolivre.mercadolivros.controller.request.PostCustomerRequest
import com.mercadolivre.mercadolivros.controller.request.PutCustomerRequest
import com.mercadolivre.mercadolivros.extension.toCustomerModel
import com.mercadolivre.mercadolivros.model.CustomerModel
import com.mercadolivre.mercadolivros.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("customer")
class CustomerController (
    val customerService: CustomerService
        ){

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id:Int): CustomerModel {
        return customerService.getById(id)
    }

    @GetMapping
    fun getAll(@RequestParam name:String?): List<CustomerModel> {
        return customerService.getAll(name)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customer: PostCustomerRequest) {
        return customerService.createCustomer(customer.toCustomerModel())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id:Int, @RequestBody customer: PutCustomerRequest) {
        return customerService.updateCustomer(customer.toCustomerModel(id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun deleteCustomer(@PathVariable id:Int) {
        return customerService.deleteCustomer(id)
    }

}
