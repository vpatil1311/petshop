package com.sogeti.coe.shop;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.sogeti.coe.shop.controller.ProductController;

public class HomeControllerTest {

	@Test
	public void testController() {
		ProductController controller = new ProductController();
		Model model = new ExtendedModelMap();
//		Assert.assertEquals("home",controller.home(model));
		
		Object message = model.asMap().get("controllerMessage");
		Assert.assertEquals(null,message);
		
	}
}
