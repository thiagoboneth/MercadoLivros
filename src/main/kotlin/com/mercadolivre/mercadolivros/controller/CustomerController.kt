package com.mercadolivre.mercadolivros.controller

import com.mercadolivre.mercadolivros.controller.request.PostCustomerRequest
import com.mercadolivre.mercadolivros.controller.request.PutCustomerRequest
import com.mercadolivre.mercadolivros.model.CustomerModel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("customer")
class CustomerController {

    val customers = mutableListOf<CustomerModel>()

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id:String): CustomerModel {
        return customers.filter { it.id == id }.first()
    }

    @GetMapping
    fun getAll(@RequestParam name:String?): List<CustomerModel> {
        name?.let {
            return  customers.filter { it.name.contains(name, ignoreCase = true) }
        }
        return customers
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customer: PostCustomerRequest) {
        val id =if (customers.isEmpty()){
            1
        }else{
            customers.last().id.toInt()+1
        }.toString()
        customers.add(CustomerModel(id,customer.name,customer.email))
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id:String, @RequestBody customer: PutCustomerRequest) {
        customers.filter { it.id == id }.first().let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun deleteCustomer(@PathVariable id:String) {
        customers.removeIf { it.id == id }
    }

}