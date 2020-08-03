package com.sg.shopping.model;

import lombok.Data;

@Data
public class ProductCartModel {

	private int productId;

	private String productName;

	private int qty;

	private double price;

	private double totalPrice;

}
