package com.sg.shopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.shopping.exception.ProcessException;
import com.sg.shopping.model.ProductModel;
import com.sg.shopping.service.ProductService;

/**
 * Product related Controller
 * 
 * @author Samuel
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {

	// Logger
	Logger logger = LoggerFactory.getLogger(ProductController.class);

	// product service
	@Autowired
	private ProductService productService;

	/**
	 * Get all Active Products
	 * 
	 * @return {@link Iterable} of {@link ProductModel}
	 */
	@GetMapping
	public ResponseEntity<Iterable<ProductModel>> findAll() {
		logger.debug("find all products");

		try {
			return new ResponseEntity<>(productService.findActive(), HttpStatus.OK);
		} catch (ProcessException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessException(e.getMessage());
		}
	}
}
