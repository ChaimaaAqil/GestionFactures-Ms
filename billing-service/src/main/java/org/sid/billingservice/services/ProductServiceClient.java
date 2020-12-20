package org.sid.billingservice.services;


import org.sid.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.ws.rs.QueryParam;

@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductServiceClient {
    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable(name = "id") Long id);

    @GetMapping("/products")
    public PagedModel<Product> getAllProduct();
    //public PagedModel<Product> getAllProduct(@QueryParam(value = "page") int page,@QueryParam(value = "size") int size);
}
