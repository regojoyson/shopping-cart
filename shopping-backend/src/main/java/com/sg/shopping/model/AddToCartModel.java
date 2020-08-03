package com.sg.shopping.model;

import lombok.Data;

@Data
public class AddToCartModel {

	// for temporary we have only one cart
	private int cartId = 1;

	private int productId;

	private int qty=1;

}
