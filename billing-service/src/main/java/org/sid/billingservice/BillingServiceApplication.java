package org.sid.billingservice;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repositories.BillRepository;
import org.sid.billingservice.repositories.ProductItemRepository;
import org.sid.billingservice.services.CustomerServiceClient;
import org.sid.billingservice.services.ProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
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
