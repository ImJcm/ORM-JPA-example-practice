package com.imjcm.ormjpaexamplepractice.prac.repository;

import com.imjcm.ormjpaexamplepractice.prac.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
