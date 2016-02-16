package eu.learnpad.simulator.mon.coverage;

public class Category {

	private int id;
	private String category_name;
	
	public Category(int id, String categoryName) {
		this.id = id;
		this.category_name = categoryName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
}
