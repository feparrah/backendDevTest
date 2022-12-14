package com.backendtest.util;

import com.backendtest.model.ProductDetail;

public class Generator {

	public static ProductDetail genProduct() {

		return ProductDetail.builder().id("1").name("p1").availability(true).price(10.0).build();

	}

}
