package com.sg.shopping.service;

import com.sg.shopping.model.ProductModel;

/**
 * Product related business logic
 * 
 * @author Samuel
 *
 */
public interface ProductService {

	/**
	 * Find all active products
	 * 
	 * @return {@link Iterable} of {@link ProductModel}
	 */
	Iterable<ProductModel> findActive();
}
