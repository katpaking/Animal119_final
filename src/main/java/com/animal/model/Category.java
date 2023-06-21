package com.animal.model;

public class Category {
	
	
	private String catename;
	
	public Category(String catename) {
		super();
		
		this.catename = catename;
		
	}

	

	public String getCatename() {
		return catename;
	}

	public void setCatename(String catename) {
		this.catename = catename;
	}

	public Category() {
	super();
	}



	@Override
	public String toString() {
		return "Category [catename=" + catename + "]";
	}

	
	

}
