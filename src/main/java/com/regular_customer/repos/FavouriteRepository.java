package com.regular_customer.repos;

import com.regular_customer.domain.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FavouriteRepository extends JpaRepository<Favourite, Integer> {
}
