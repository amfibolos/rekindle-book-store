package com.rekindle.book.store.customer.orm.repository;


import com.rekindle.book.store.customer.orm.entity.CustomerEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {


}
