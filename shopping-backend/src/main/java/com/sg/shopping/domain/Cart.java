package com.sg.shopping.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Cart Entity
 * 
 * @author Samuel
 *
 */
@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 10, unique = true, nullable = false, insertable = true, updatable = false)
	private int id;

	@Column(name = "created_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime createdDate;

	@Column(name = "status")
	private String status;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id", foreignKey = @ForeignKey(name = "FK_PC_ID"))
	private List<ProductCart> productCarts = new ArrayList<>();

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param createdDate
	 * @param status
	 */
	public Cart(int id, LocalDateTime createdDate, String status) {
		this.id = id;
		this.createdDate = createdDate;
		this.status = status;
	}

	/**
	 * Total Price
	 * 
	 * @return
	 */
	public Double getTotalOrderPrice() {
		double sum = 0D;
		List<ProductCart> orderProducts = getProductCarts();
		for (ProductCart pc : orderProducts) {
			sum += pc.getTotalPrice();
		}
		return sum;
	}

	/**
	 * Total product
	 * 
	 * @return
	 */
	public int getNumberOfProducts() {
		return this.productCarts.size();
	}

}
