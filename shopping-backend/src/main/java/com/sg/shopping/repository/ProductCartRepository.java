package com.sg.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sg.shopping.domain.ProductCart;

/**
 * 
 * Product cart related repository
 * 
 * @author Samuel
 * 
 */
public interface ProductCartRepository extends JpaRepository<ProductCart, Integer> {

}
