package com.nutsaboutcandy.entities.categories;

import java.io.Serializable;
import java.math.BigDecimal;

public class Category implements Serializable{

	private static final long serialVersionUID = -5922252437995941706L;
	
	private String type;
	private BigDecimal largePrice;
	private BigDecimal mediumPrice;
	private BigDecimal smallPrice;
	
	public final String PREMIUM = "Premium";
	public final String REGULAR = "Regular";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (type == null || type.trim().length() == 0) {
			// TODO What should this do?
		}
		if (type.equalsIgnoreCase(REGULAR)) {
			this.type = REGULAR;
			this.setRegularPrice();
		} else if (type.equalsIgnoreCase(PREMIUM)) {
			this.type = PREMIUM;
			this.setPremiumPrice();
		} else {
			this.type = "";
		}
	}

	public BigDecimal getMediumPrice() {
		return mediumPrice;
	}

	private void setMediumPrice(BigDecimal mediumPrice) {
		this.mediumPrice = mediumPrice;
	}

	public BigDecimal getSmallPrice() {
		return smallPrice;
	}

	private void setSmallPrice(BigDecimal smallPrice) {
		this.smallPrice = smallPrice;
	}

	public BigDecimal getLargePrice() {
		return largePrice;
	}

	private void setLargePrice(BigDecimal largePrice) {
		this.largePrice = largePrice;
	}
	
	private void setPremiumPrice() {
		this.setSmallPrice(new BigDecimal("70.00"));
		this.setMediumPrice(new BigDecimal("120.00"));
		this.setLargePrice(new BigDecimal("170.00"));
	}

	private void setRegularPrice() {
		this.setSmallPrice(new BigDecimal("50.00"));
		this.setMediumPrice(new BigDecimal("100.00"));
		this.setLargePrice(new BigDecimal("150.00"));
	}

}
