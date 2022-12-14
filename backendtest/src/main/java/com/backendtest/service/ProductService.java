package com.backendtest.service;

import java.util.List;

import com.backendtest.model.ProductDetail;

public interface ProductService {
	
	public List<ProductDetail> getSimilarProductsById(String productId);

}
