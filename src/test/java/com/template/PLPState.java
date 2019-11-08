package com.template;

import java.util.List;

public class PLPState {
	
	private int productIndex;
	private int swatchIndex;
	private String originalColor;
	private List<String> originalProductNames;
	
	public int getProductIndex() {
		return productIndex;
	}
	public void setProductIndex(int productIndex) {
		this.productIndex = productIndex;
	}
	public int getSwatchIndex() {
		return swatchIndex;
	}
	public void setSwatchIndex(int swatchIndex) {
		this.swatchIndex = swatchIndex;
	}
	public String getOriginalColor() {
		return originalColor;
	}
	public void setOriginalColor(String originalColor) {
		this.originalColor = originalColor;
	}
	public List<String> getOriginalProductNames() {
		return originalProductNames;
	}
	public void setOriginalProductNames(List<String> originalProductNames) {
		this.originalProductNames = originalProductNames;
	}
}
