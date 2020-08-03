package com.sg.shopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.shopping.exception.ProcessException;
import com.sg.shopping.model.AddToCartModel;
import com.sg.shopping.model.CartModel;
import com.sg.shopping.service.CartService;


/**
 * Order related controler
 * 
 * @author Samuel
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api/order")
public class OrderController {

	// Logger
	Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private CartService cartService;

	@PatchMapping
	public ResponseEntity<CartModel> updateCart(@RequestBody AddToCartModel addToCartModel) {
		logger.debug("add/update item to cart");
		try {
			return new ResponseEntity<>(cartService.updateCart(addToCartModel), HttpStatus.CREATED);
		} catch (ProcessException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessException(e.getMessage());
		}
	}

	/**
	 * Get Cart by id
	 * 
	 * @return {@link CartModel}
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CartModel> findById(@PathVariable("id") int cartId) {
		logger.debug("Get cart by id");
		try {
			return new ResponseEntity<>(cartService.get(cartId), HttpStatus.OK);
		} catch (ProcessException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessException(e.getMessage());
		}
	}
}
