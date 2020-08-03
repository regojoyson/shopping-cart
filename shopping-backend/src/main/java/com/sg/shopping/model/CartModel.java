package com.sg.shopping.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CartModel {

	private int id;

	private String status;

	private List<ProductCartModel> productCartItems = new ArrayList<>();

	private double totalPrice;

	private int totalItems;
}
