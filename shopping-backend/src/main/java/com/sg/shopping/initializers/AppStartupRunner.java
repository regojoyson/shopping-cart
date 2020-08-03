package com.sg.shopping.initializers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.sg.shopping.domain.Cart;
import com.sg.shopping.domain.Product;
import com.sg.shopping.repository.CartRepository;
import com.sg.shopping.repository.ProductRepository;

import static com.sg.shopping.constants.Status.CREATED;

/**
 * 
 * Run initial scripts here
 * 
 * @author Samuel
 *
 */
@Component
public class AppStartupRunner implements ApplicationRunner {

	// product repository
	@Autowired
	private ProductRepository productRepository;

	// cart repository
	@Autowired
	private CartRepository cartRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// add products
		productRepository.save(new Product(1, "Pen", 10.5D, true));
		productRepository.save(new Product(2, "Book", 10D, true));
		productRepository.save(new Product(3, "Scale", 1D, true));
		// create one cart
		cartRepository.save(new Cart(1, LocalDateTime.now(), CREATED.name()));

	}
}