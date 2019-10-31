package helpers;

import java.util.ArrayList;
import java.util.Map;

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
		long totalQuantity = (long) executor.executeScript(
				"var shopper = localStorage.getItem('pink-shopper');\n" + 
				"shopper = JSON.parse(shopper);\n" + 
				"var count = 0;\n" + 
				"for(i=0; i<shopper['bag']['lineItems'].length; i++) {\n" + 
				"count += shopper['bag']['lineItems'][i]['quantity'];\n" + 
				"};\n" + 
				"return count;");
		return Math.toIntExact(totalQuantity);
	}
	
	// get the quantity of a particular lineItem in the cart
	public static int getItemQuantity(int lineItem) {
		JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getDriver();
		long quantity = (long) executor.executeScript(
				"var shopper = localStorage.getItem('pink-shopper');\n" + 
				"shopper = JSON.parse(shopper);\n" + 
				"return shopper['bag']['lineItems']["+lineItem+"]['quantity'];");
		return Math.toIntExact(quantity);
	}
	
	// get the price of a particular lineItem in the cart
	public static int getItemPrice(int lineItem) {
		JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getDriver();
		long price = (long) executor.executeScript(
				"var shopper = localStorage.getItem('pink-shopper');\n" + 
				"shopper = JSON.parse(shopper);\n" + 
				"return shopper['bag']['lineItems']["+lineItem+"]['price']['value'];");
		return Math.toIntExact(price);
	}
	
	// get the price of a line item	in cents
	public static int getLineItemPrice(int index) {
		JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getDriver();
		long price = (long) executor.executeScript(
				"var shopper = localStorage.getItem('pink-shopper');\n" + 
				"shopper = JSON.parse(shopper);\n" + 
				"return shopper['bag']['lineItems']["+index+"]['price']['value']['centAmount'];");
		return Math.toIntExact(price);
	}
	
	// get the total price of a line item in cents
	public static int getLineItemTotalPrice(int index) {
		JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getDriver();
		long totalPrice = (long) executor.executeScript(
				"var shopper = localStorage.getItem('pink-shopper');\n" + 
				"shopper = JSON.parse(shopper);\n" + 
				"return shopper['bag']['lineItems']["+index+"]['totalPrice']['centAmount'];");
		return Math.toIntExact(totalPrice);
		}
		
	// get the total price of the cart	
	public static int getTotalPrice() {
		JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getDriver();
		long price = (long) executor.executeScript(
				"var shopper = localStorage.getItem('pink-shopper');\n" + 
				"shopper = JSON.parse(shopper);\n" + 
				"return shopper['bag']['totalPrice']['centAmount'];");
		return Math.toIntExact(price);
	}
	
	// get an ArrayList of the line item's attributes	
	@SuppressWarnings("unchecked")
	public static ArrayList<Map<String, Object>> getLineItemAttributes(int index) {
		JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getDriver();
		Object attributes = (Object) executor.executeScript(
				"var shopper = localStorage.getItem('pink-shopper');\n" + 
				"shopper = JSON.parse(shopper);\n" + 
				"return shopper['bag']['lineItems']["+index+"]['variant']['attributes'];");
		ArrayList<Map<String, Object>> attList = new ArrayList<Map<String, Object>>();
		attList = (ArrayList<Map<String, Object>>) attributes;
		return attList;
	}
	
	// return a specific lineItem attribute's value from it's name
	public static String getAttributeValue(ArrayList<Map<String, Object>> attributes, String name) {
		String value = "Not Found";
		for (Map<String, Object> map : attributes) {
			if (map.get("name").equals(name)) {
				value = map.get("value").toString();
				break;
			}
		}
		return value;
	}

	public static String getLineItemAttribute(int index, String attributeName) {
		JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getDriver();
		String attributeValue = (String) executor.executeScript(
				"var shopper = localStorage.getItem('pink-shopper');\n" + 
				"shopper = JSON.parse(shopper); \n" + 
				"shopper = shopper['bag']['lineItems']['"+index+"']['variant']['attributes'];\n" + 
				"for (i = 0; i<shopper.length ; i++) {\n" + 
				"	if (shopper[i].name == '"+attributeName+"') {\n" + 
				"		return shopper[i].value;\n" + 
				"        }\n" + 
				"}");
		return attributeValue;
	}
}
