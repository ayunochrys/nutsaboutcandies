package com.nutsaboutcandy.entities.categories;

import java.math.BigDecimal;

import com.nutsaboutcandy.entities.items.Item;
import com.nutsaboutcandy.entities.items.Nut;

public class NutsCategory extends Category {
	
	private static final long serialVersionUID = -6430240380235868183L;

	public Boolean isANut(Item item){
		if (item instanceof Nut) {
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
