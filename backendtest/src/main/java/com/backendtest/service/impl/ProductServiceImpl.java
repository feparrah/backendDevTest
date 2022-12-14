package com.backendtest.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.backendtest.client.ProductsClient;
import com.backendtest.model.ProductDetail;
import com.backendtest.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductsClient client;

	public List<ProductDetail> getSimilarProductsById(String productId) {

		log.info("Getting similars product by id: {}", productId);

		return client.getSimilarProductsIds(productId).stream().parallel()
				.map(p -> CompletableFuture.supplyAsync(() -> getProductDetailById(p)))
				.map(f -> {
					try {
						return f.join();
					} catch (Exception e) {
						log.info("Error client get product detail: {}", e.getMessage());
					}
					return null;
				}).filter(Objects::nonNull)
				.collect(Collectors.toList());

	}
	
	@Cacheable
	private ProductDetail getProductDetailById(String id) {
		log.info("Getting product detail by id: {}", id);
		return client.getProductById(id);
	}

}
