package org.sig.billingserviceapp.repositories;

import org.sig.billingserviceapp.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BillRepository extends JpaRepository<Bill,Long> {
}
