package com.nutsaboutcandy.entities;

import java.io.Serializable;

public class InventoryPackages implements Serializable{
	
	private static final long serialVersionUID = -5900032989348397460L;
	private Product product;
	private String size;
	private Integer weighInGrams;
	private Integer numberOfStock;
	
	public InventoryPackages(Product product, String size,Integer numberOfStocks){
		setProduct(product);
		setSize(size);
		setNumberOfStock(numberOfStocks);
	}
	
	public Product getProduct() {
		return product;
	}
	public String getSize() {
		return size;
	}
	public Integer getWeighInGrams() {
		return weighInGrams;
	}
	public Integer getNumberOfStock() {
		return numberOfStock;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setSize(String size) {
		this.size = size;
		
		if(size.equalsIgnoreCase("Small")){
			this.weighInGrams = 50;
		}
		
		else if(size.equalsIgnoreCase("Medium")){
			this.weighInGrams = 100;
		}
	
		else if(size.equalsIgnoreCase("Large")){
			this.weighInGrams = 150;
		}
	}
	public void setWeighInGrams(Integer weighInGrams) {
		this.weighInGrams = weighInGrams;
	}
	public void setNumberOfStock(Integer numberOfStock) {
		this.numberOfStock = numberOfStock;
	}
	
	

}
