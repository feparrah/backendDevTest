package com.backendtest.client;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.backendtest.model.ProductDetail;
import com.backendtest.util.Route;

@FeignClient(name = Route.PRODUCTS_CLIENT_NAME,
url = Route.PRODUCTS_CLIENT_URL)
public interface ProductsClient {
	
	@GetMapping(Route.GET_SIMILAR_PRODUCTS_IDS_PATH)
    List<String> getSimilarProductsIds(@PathVariable String productId);

	@GetMapping(Route.GET_PRODUCT_BY_ID_PATH)
    ProductDetail getProductById(@PathVariable String productId);

}
