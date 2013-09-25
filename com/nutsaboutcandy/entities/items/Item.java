package com.nutsaboutcandy.entities.items;

import java.io.Serializable;

public class Item implements Serializable{
	

	private static final long serialVersionUID = 2753881151980736941L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
