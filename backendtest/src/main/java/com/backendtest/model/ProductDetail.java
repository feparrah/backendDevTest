package com.backendtest.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDetail {

	private String id;
	private String name;
	private Double price;
	private Boolean availability;
}
