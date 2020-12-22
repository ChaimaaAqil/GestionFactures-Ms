package org.sig.billingserviceapp;

import org.sig.billingserviceapp.entities.Bill;
import org.sig.billingserviceapp.entities.ProductItem;
import org.sig.billingserviceapp.model.Customer;
import org.sig.billingserviceapp.model.Product;
import org.sig.billingserviceapp.repositories.BillRepository;
import org.sig.billingserviceapp.repositories.ProductItemRepository;
import org.sig.billingserviceapp.services.CustomerServiceClient;
import org.sig.billingserviceapp.services.ProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceAppApplication.class, args);
    }
    @Autowired
    CustomerServiceClient customerServiceClient;
    @Autowired
    ProductServiceClient productServiceClient;
    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository){
        System.out.printf("*********************");
        return args -> {
            // get customer from RestClient
            Customer customer = customerServiceClient.findCustomerById(1L);
            Bill b1 =  billRepository.save(new Bill(null,new Date(),customer.getId(),null,customer));
            PagedModel<Product> productPagedModel = productServiceClient.getAllProduct();
            productPagedModel.forEach(product -> {
                ProductItem productItem= new ProductItem();
                productItem.setPrice(product.getPrice());
                productItem.setProductID(product.getId());
                productItem.setQuantity(1+new Random().nextInt(100));
                productItem.setBill(b1);
                productItemRepository.save(productItem);

            });

        };
    }


}
