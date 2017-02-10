package Himanshu.EbayProj;

import org.testng.annotations.Test;

public class Product {
	public String price;
	public String productName;
	public String sellerName;
	
	@Test
	public Product(String price, String productName, String sellerName) {
	
		this.price = price;
		this.productName = productName;
		this.sellerName = sellerName;
	}
	
	
}
