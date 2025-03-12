package com.imjcm.ormjpaexamplepractice.prac.repository;

import com.imjcm.ormjpaexamplepractice.prac.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}
