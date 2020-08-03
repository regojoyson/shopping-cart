package com.sg.shopping.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

/**
 * 
 * @author Samuel
 *
 */
@Entity
@Table(name = "product_cart", uniqueConstraints = {
		@UniqueConstraint(name = "PRODUCT_CART_UK", columnNames = { "cart_id", "product_id" }) })
@Data
public class ProductCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 10, unique = true, nullable = false, insertable = true, updatable = false)
	private int id;

	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "cart_id", foreignKey = @ForeignKey(name = "FK_PC_ID"))
	private Cart cart;

	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_PC_PRODUCT_ID"))
	private Product product;

	@Column(name = "qty")
	private int quantity;

	/**
	 * Get total price of the account
	 * 
	 * @return
	 */
	public Double getTotalPrice() {
		return getProduct().getPrice() * getQuantity();
	}

}
