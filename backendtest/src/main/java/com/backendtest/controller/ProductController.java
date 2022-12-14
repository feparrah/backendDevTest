package com.backendtest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.backendtest.model.ProductDetail;
import com.backendtest.service.ProductService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("/product/{productId}/similar")
	public ResponseEntity<List<ProductDetail>> getSimilarProducts(@PathVariable String productId) {
		
		log.info("Entering get similar products...");

		return ResponseEntity.ok(service.getSimilarProductsById(productId));
	}

}
