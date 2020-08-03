package com.sg.shopping.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sg.shopping.domain.Product;
import com.sg.shopping.model.ProductModel;
import com.sg.shopping.repository.ProductRepository;
import com.sg.shopping.service.ProductService;

/**
 * @see {@link ProductService}
 * 
 * @author Samuel
 *
 */
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

	// Product repository
	@Autowired
	private ProductRepository productRepository;

	// Logger
	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	/**
	 * @see {@link ProductService#findActive()}
	 */
	@Override
	public Iterable<ProductModel> findActive() {
		logger.debug("get all product list");
		return getProducts(productRepository.findAllByActive(true));
	}

	/************ Private Methods *************/

	/**
	 * Get the list of products
	 * 
	 * @param products
	 * @return {@link List} of {@link ProductModel}
	 */
	private Iterable<ProductModel> getProducts(List<Product> products) {
		List<ProductModel> productList = new ArrayList<>();
		for (Product product : products) {
			ProductModel productModel = new ProductModel();
			productModel.setActive(product.isActive());
			productModel.setId(product.getId());
			productModel.setName(product.getName());
			productList.add(productModel);
		}
		return productList;
	}

}
