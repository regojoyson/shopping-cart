package com.sg.shopping.service;

import com.sg.shopping.model.AddToCartModel;
import com.sg.shopping.model.CartModel;

/**
 * Cart Service
 * 
 * @author Samuel
 *
 */
public interface CartService {

	/**
	 * Update Cart with items
	 * 
	 * @param addToCartModel
	 * @return {@link CartModel}
	 */
	CartModel updateCart(AddToCartModel addToCartModel);

	/**
	 * Get Cart Id
	 * 
	 * @param cartId
	 * @return {@link CartModel}
	 */
	CartModel get(int cartId);

}
