package com.nutsaboutcandy.entities;

import java.io.Serializable;
import java.util.ArrayList;

import com.nutsaboutcandy.entities.categories.Category;
import com.nutsaboutcandy.entities.items.Item;

public class Product implements Serializable{
	
	private static final long serialVersionUID = 1970885551732009173L;
	private String name;
	private Category category;
	private ArrayList<Item> items;
	
	public Product(String name,Category category,ArrayList<Item> items){
		setName(name);
		setCategory(category);
		setItems(items);
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public String getName() {
		return name;
	}
	public Category getCategory() {
		return category;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
}
