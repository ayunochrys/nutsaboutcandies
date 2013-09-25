package com.nutsaboutcandy.entities.categories;

import java.math.BigDecimal;

import com.nutsaboutcandy.entities.items.Candy;
import com.nutsaboutcandy.entities.items.Item;

public class CandyCategory extends Category {
	
	private static final long serialVersionUID = 4804114931704175208L;

	public Boolean isACandy(Item item){
		if (item instanceof Candy) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String getType() {
		return super.getType();
	}

	@Override
	public void setType(String type) {
		super.setType(type);
	}

	@Override
	public BigDecimal getMediumPrice() {
		return super.getMediumPrice();
	}

	@Override
	public BigDecimal getSmallPrice() {
		return super.getSmallPrice();
	}

	@Override
	public BigDecimal getLargePrice() {
		return super.getLargePrice();
	}

}
