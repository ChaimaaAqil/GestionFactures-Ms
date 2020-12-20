package org.sid.customerservice;

import org.sid.customerservice.entities.Customer;
import org.sid.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerRepository customerRepository,RepositoryRestConfiguration configuration){
        return args -> {
            configuration.exposeIdsFor(Customer.class);
            customerRepository.save(new Customer(null,"ALAA","alaaadoui@gmail.com"));
            customerRepository.save(new Customer(null,"Chaimaa","chaimaa.aqil@gmail.com"));
            customerRepository.save(new Customer(null,"Youness","youness.aithaba@gmail.com"));
            customerRepository.findAll().forEach(customer -> {
                System.out.println(customer.toString());
            });
        };

    }

}
