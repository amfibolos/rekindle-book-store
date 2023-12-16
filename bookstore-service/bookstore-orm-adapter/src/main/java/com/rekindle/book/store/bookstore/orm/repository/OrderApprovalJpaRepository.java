package com.rekindle.book.store.bookstore.orm.repository;


import com.rekindle.book.store.bookstore.orm.entity.OrderApprovalEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderApprovalJpaRepository extends JpaRepository<OrderApprovalEntity, UUID> {


}
