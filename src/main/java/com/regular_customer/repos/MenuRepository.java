package com.regular_customer.repos;

import com.regular_customer.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
