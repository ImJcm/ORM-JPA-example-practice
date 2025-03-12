package com.imjcm.ormjpaexamplepractice.prac.repository;

import com.imjcm.ormjpaexamplepractice.prac.domain.CartFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartFoodRepository extends JpaRepository<CartFood, Long> {
}
