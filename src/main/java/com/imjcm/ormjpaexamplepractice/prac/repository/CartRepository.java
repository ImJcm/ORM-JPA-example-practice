package com.imjcm.ormjpaexamplepractice.prac.repository;

import com.imjcm.ormjpaexamplepractice.prac.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
