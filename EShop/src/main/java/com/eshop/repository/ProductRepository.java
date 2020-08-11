package com.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.model.Product;
/**
 * <p>
 * The repository interface for product activities.
 * </p>
 * 
 * @author Sreejith
 * @version 1.0
 * @since July 2020
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
