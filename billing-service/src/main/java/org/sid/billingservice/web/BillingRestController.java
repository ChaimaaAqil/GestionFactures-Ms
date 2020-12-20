package org.sid.billingservice.web;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.repositories.BillRepository;
import org.sid.billingservice.repositories.ProductItemRepository;
import org.sid.billingservice.services.CustomerServiceClient;
import org.sid.billingservice.services.ProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class BillingRestController {
    @Autowired
    BillRepository billRepository;
    @Autowired
    ProductItemRepository productItemRepository;
    @Autowired
    ProductServiceClient productServiceClient;
    @Autowired
    CustomerServiceClient customerServiceClient;

    @GetMapping("/bills/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id){
        Bill bill=billRepository.findById(id).get();
        bill.setCustomer(customerServiceClient.findCustomerById(bill.getCustomerID()));
        bill.setProductItems(productItemRepository.findByBillId(id));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(productServiceClient.findProductById(productItem.getId()));
        });
        return bill;
    }
    @GetMapping("/bills")
    public List<Bill> getBills(){
       return billRepository.findAll();
    }
}
