package com.sg.shopping.service.impl;

import java.util.Optional;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sg.shopping.domain.Cart;
import com.sg.shopping.domain.Product;
import com.sg.shopping.domain.ProductCart;
import com.sg.shopping.exception.ProcessException;
import com.sg.shopping.model.AddToCartModel;
import com.sg.shopping.model.CartModel;
import com.sg.shopping.model.ProductCartModel;
import com.sg.shopping.repository.CartRepository;
import com.sg.shopping.repository.ProductRepository;
import com.sg.shopping.service.CartService;

/**
 * 
 * @see CartService
 * 
 * @author Samuel
 *
 */
@Transactional(readOnly = true)
@Service
public class CartServiceImpl implements CartService {

	// Logger
	Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

	// Cart Repository
	@Autowired
	private CartRepository cartRepository;

	// Product Repository
	@Autowired
	private ProductRepository productRepository;

	/**
	 * @see {@link CartService#updateCart(AddToCartModel)}
	 */
	@Override
	@Transactional(readOnly = false)
	public CartModel updateCart(AddToCartModel addToCartModel) {
		logger.debug("Add/update item to cart");
		// get card
		Optional<Cart> cartOpt = cartRepository.findById(addToCartModel.getCartId());
		if (cartOpt.isEmpty()) {
			throw new ProcessException("Cart Not Found");
		}
		Optional<Product> productOpt = productRepository.findById(addToCartModel.getProductId());
		if (productOpt.isEmpty())
			throw new ProcessException("Product  Not Found");
		Cart cart = cartOpt.get();
		// get the the product card if already exist
		ProductCart productCart = null;
		for (ProductCart pc : cart.getProductCarts()) {
			if (pc.getProduct().getId() == addToCartModel.getProductId()) {
				productCart = pc;
				break;
			}
		}
		// if present update it else create
		if (productCart != null) {
			cart.getProductCarts().remove(productCart);
			int qty = productCart.getQuantity() + addToCartModel.getQty();
			if (qty > 0) {
				productCart.setQuantity(qty);
				cart.getProductCarts().add(productCart);
			}
		} else {
			if (addToCartModel.getQty() > 0) {
				productCart = new ProductCart();
				productCart.setQuantity(addToCartModel.getQty());
				productCart.setProduct(productOpt.get());
				productCart.setCart(cart);
				cart.getProductCarts().add(productCart);
			}
		}
		cart = cartRepository.save(cart);

		// get fresh copy and return the data
		return getCartModel(cart);

	}

	/**
	 * @see {@link CartService#get(int)}
	 */
	@Override
	public CartModel get(int cartId) {
		logger.debug("Get cart");
		// get card
		Optional<Cart> cartOpt = cartRepository.findById(cartId);
		if (cartOpt.isEmpty()) {
			throw new ProcessException("Cart Not Found");
		}
		return getCartModel(cartOpt.get());
	}

	/******** Private Methods ***********/

	/**
	 * Get Cart Model
	 * 
	 * @param cart
	 * @return {@link CartModel}
	 */
	private CartModel getCartModel(Cart cart) {

		CartModel cartModel = new CartModel();
		cartModel.setId(cart.getId());
		cartModel.setStatus(cart.getStatus());
		cartModel.setTotalItems(cart.getNumberOfProducts());
		cartModel.setTotalPrice(cart.getTotalOrderPrice());
		Hibernate.initialize(cart.getProductCarts());
		for (ProductCart pc : cart.getProductCarts()) {
			ProductCartModel pcm = new ProductCartModel();
			pcm.setPrice(pc.getProduct().getPrice());
			pcm.setProductName(pc.getProduct().getName());
			pcm.setProductId(pc.getProduct().getId());
			pcm.setQty(pc.getQuantity());
			pcm.setTotalPrice(pc.getTotalPrice());
			cartModel.getProductCartItems().add(pcm);
		}
		return cartModel;
	}

}
