package com.template;

public class BagState {

	private int lineItems;
	private int itemQuantity;
	private int productsDisplayed;
	private String maxGiftMessage = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta.";
	private String giftMessage;
	
	
	public int getLineItems() {
		return lineItems;
	}

	public void setLineItems(int lineItems) {
		this.lineItems = lineItems;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public int getProductsDisplayed() {
		return productsDisplayed;
	}

	public void setProductsDisplayed(int productsDisplayed) {
		this.productsDisplayed = productsDisplayed;
	}

	public String getMaxGiftMessage() {
		return maxGiftMessage;
	}
	
	public String getGiftMessage() {
		return giftMessage;
	}

	public void setGiftMessage(String giftMessage) {
		this.giftMessage = giftMessage;
	}
	
	
}
