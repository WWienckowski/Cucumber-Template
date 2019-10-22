package helpers;

import org.openqa.selenium.JavascriptExecutor;

import driver.DriverFactory;

public class Cart {
	
	public static void makeCart(String token) {
		JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getDriver();
		String cart = (String) executor.executeAsyncScript(
				"var callback = arguments[arguments.length - 1];" +
				"var xhr = new XMLHttpRequest();\n" + 
				"xhr.open('POST', 'https://api.sphere.io/pink-shirtmaker-dev/carts', true);\n" +  
				"xhr.setRequestHeader('Authorization', 'Bearer "+token+"' );\n" +
				"xhr.onreadystatechange = function() {" +
			       "  if (xhr.readyState == 4) {" +
			       "    callback(xhr.responseText);" +
			       "  }" +
				"};\n" + 
				"\n" + 
				"xhr.send('{\"currency\":\"USD\",\"country\":\"US\",\"taxMode\":\"External\"}');");
		System.out.println(cart);
		executor.executeScript(
				"var existing = localStorage.getItem('pink-shopper');\n" +
			    "existing = JSON.parse(existing);\n" +
			    "existing['bag'] = "+cart+";\n" +
			    "localStorage.setItem('pink-shopper', JSON.stringify(existing));");
	}
	
	// returns the amount of line items in the cart
	public static int getLineItemCount() {
		JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getDriver();
		long lineItems = (long) executor.executeScript(
				"var shopper = localStorage.getItem('pink-shopper');\n" + 
				"shopper = JSON.parse(shopper);\n" + 
				"return(shopper['bag']['lineItems'].length);");
		return Math.toIntExact(lineItems);
	}
	
	// adds the quantity of all line items in the cart and returns the total
	public static int getTotalQuantity() {
		JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getDriver();
		int totalQuantity = (int) executor.executeScript(
				"var shopper = localStorage.getItem('pink-shopper');\n" + 
				"shopper = JSON.parse(shopper);\n" + 
				"var count = 0;\n" + 
				"for(i=0; i<shopper['bag']['lineItems'].length; i++) {\n" + 
				"count += shopper['bag']['lineItems'][i]['quantity'];\n" + 
				"};\n" + 
				"return count;");
		return totalQuantity;
	}
	
	// get the quantity of a particular lineItem in the cart
	public static int getItemQuantity(int lineItem) {
		JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getDriver();
		int quantity = (int) executor.executeScript(
				"var shopper = localStorage.getItem('pink-shopper');\n" + 
				"shopper = JSON.parse(shopper);\n" + 
				"return shopper['bag']['lineItems']["+lineItem+"]['quantity'];");
		return quantity;
	}
	
	// get the price of a particular lineItem in the cart
	public static int getItemPrice(int lineItem) {
		JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getDriver();
		int quantity = (int) executor.executeScript(
				"var shopper = localStorage.getItem('pink-shopper');\n" + 
				"shopper = JSON.parse(shopper);\n" + 
				"return shopper['bag']['lineItems']["+lineItem+"]['price']['value'];");
		return quantity;
	}
		
	// get the total price of the cart	
	public static int getTotalPrice() {
		JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getDriver();
		int quantity = (int) executor.executeScript(
				"var shopper = localStorage.getItem('pink-shopper');\n" + 
				"shopper = JSON.parse(shopper);\n" + 
				"return shopper['bag']['totalPrice']['centAmount'];");
		return quantity;
	}
}
