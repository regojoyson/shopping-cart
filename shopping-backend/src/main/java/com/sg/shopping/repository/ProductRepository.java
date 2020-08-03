package com.sg.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sg.shopping.domain.Product;

/**
 * Product Entity related Repository
 * 
 * @see {@link Product}
 * 
 * @author Samuel
 *
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

	/**
	 * Get all items based on active condition
	 * 
	 * @param isActive
	 * @return {@link List} of {@link Product}
	 */
	List<Product> findAllByActive(boolean isActive);

}
