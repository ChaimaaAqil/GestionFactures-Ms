package org.sig.billingserviceapp.web;

import org.sig.billingserviceapp.entities.Bill;
import org.sig.billingserviceapp.repositories.BillRepository;
import org.sig.billingserviceapp.repositories.ProductItemRepository;
import org.sig.billingserviceapp.services.CustomerServiceClient;
import org.sig.billingserviceapp.services.ProductServiceClient;
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
