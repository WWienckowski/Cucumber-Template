package helpers;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;

import driver.DriverFactory;

public class Cart {

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
		int count = 0;
		int maxTries = 3;
		long quantity;
		while(true) {
			try {
				quantity = (long) executor.executeScript(
						"var shopper = localStorage.getItem('pink-shopper');\n" +
								"shopper = JSON.parse(shopper);\n" +
								"return shopper['bag']['lineItems'][" + lineItem + "]['quantity'];");
				break;
			} catch (JavascriptException js) {
				DriverFactory.getScenario().write("Attempt "+(count+1)+"/"+maxTries+" has failed.");
				if (++count == maxTries) throw js;
				Move.idleForX(3000);
			}
		}
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

	public static String getSubtotal() {
		JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getDriver();
		long subtotal = (long) executor.executeScript(
				"var shopper = localStorage.getItem('pink-shopper');\n" + 
				"shopper = JSON.parse(shopper); \n" + 
				"var items = shopper['bag']['lineItems'].length;\n" + 
				"var subtotal = 0;\n" + 
				"for (i=0; i<items; i++) {\n" + 
				"	subtotal+= shopper['bag']['lineItems'][i]['totalPrice']['centAmount']\n" + 
				"}\n" + 
				"return subtotal/100;");
		return Long.toString(subtotal);
	}
	
	public static String getNetworkLog() {
		String scriptToExecute = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getEntries() || {}; return network;";
		String netData = ((JavascriptExecutor)DriverFactory.getDriver()).executeScript(scriptToExecute).toString();
		return netData;
	}
}
