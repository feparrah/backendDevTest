package com.backendtest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backendtest.client.ProductsClient;
import com.backendtest.exception.BadRequestException;
import com.backendtest.service.impl.ProductServiceImpl;
import com.backendtest.util.Generator;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@Mock
	private ProductsClient client;

	@InjectMocks
	private ProductServiceImpl service;

	@Test
	void getSimilarProducts_SUCCESS() {
		when(client.getSimilarProductsIds(any())).thenReturn(List.of("1","2","3"));
		when(client.getProductById(any())).thenReturn(Generator.genProduct());
		var list = service.getSimilarProductsById("1");
		assertEquals(Generator.genProduct(), list.get(0));
		verify(client, times(3)).getProductById(any());
	}
	
	@Test
	void getSimilarProducts_ERROR() {
		when(client.getSimilarProductsIds(any())).thenReturn(List.of("1","2","3"));
		when(client.getProductById("1")).thenThrow(new BadRequestException("Bad Request"));
		when(client.getProductById("2")).thenReturn(Generator.genProduct());
		when(client.getProductById("3")).thenReturn(Generator.genProduct());
		var list = service.getSimilarProductsById("1");
		assertEquals(2, list.size());
	}

}
