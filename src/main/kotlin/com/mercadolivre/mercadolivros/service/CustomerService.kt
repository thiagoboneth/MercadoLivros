package com.mercadolivre.mercadolivros.service

import com.mercadolivre.mercadolivros.model.CustomerModel
import org.springframework.stereotype.Service


@Service
class CustomerService {

    val customers = mutableListOf<CustomerModel>()

    fun getAll(name:String?): List<CustomerModel> {
        name?.let {
            return  customers.filter { it.name.contains(name, ignoreCase = true) }
        }
        return customers
    }

    fun getCustomer(id:String): CustomerModel {
        return customers.filter { it.id == id }.first()
    }

    fun createCustomer(customer: CustomerModel) {
        val id =if (customers.isEmpty()){
            1
        }else{
            customers.last().id!!.toInt() +1
        }.toString()
        customers.add(customer)
    }

    fun updateCustomer(customer: CustomerModel) {
        customers.filter { it.id == customer.id }.first().let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun deleteCustomer(id:String) {
        customers.removeIf { it.id == id }
    }
}
