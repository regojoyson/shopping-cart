package com.sg.shopping.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Product Entity
 * 
 * @author Samuel
 *
 */
@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 10, unique = true, nullable = false, insertable = true, updatable = false)
	private int id;

	@Column(name = "name", nullable = false, length = 200)
	private String name;

	@Column(name = "price", length = 20, nullable = true, precision = 2)
	private Double price;

	@Column(name = "active")
	private boolean active = true;

}
