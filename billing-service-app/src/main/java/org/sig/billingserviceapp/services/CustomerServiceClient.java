package org.sig.billingserviceapp.services;


import org.sig.billingserviceapp.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerServiceClient {
    @GetMapping("customers/{id}")
    public Customer findCustomerById(@PathVariable(name = "id") Long id);
}
