package com.imjcm.ormjpaexamplepractice.prac.repository;

import com.imjcm.ormjpaexamplepractice.prac.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
