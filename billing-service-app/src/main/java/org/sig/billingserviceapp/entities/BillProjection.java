package org.sig.billingserviceapp.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;

@Projection(name = "fullBill",types = Bill.class)
public interface BillProjection extends Projection {
    public Long getId();
    public Date getBillingDate();
    public Long getCustomer();
    public Collection<ProductItem> getProductItems();
}
