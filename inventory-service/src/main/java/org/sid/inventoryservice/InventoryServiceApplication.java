package org.sid.inventoryservice;

import org.sid.inventoryservice.entities.Product;
import org.sid.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration configuration){
        return args -> {
            configuration.exposeIdsFor(Product.class);
            productRepository.save(new Product(null,"SMARTPHONE",(int) (Math.random()*1000),(int)(Math.random()*10)));
            productRepository.save(new Product(null,"PC",(int)(Math.random()*1000),(int)(Math.random()*10)));
            productRepository.save(new Product(null,"AIRPODS",(int)(Math.random()*1000),(int)(Math.random()*10)));

            productRepository.findAll().forEach(product -> {
                System.out.println(product.toString());
            });
        };
    }

}
