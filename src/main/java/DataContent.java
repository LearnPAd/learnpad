import java.io.Serializable;


public class DataContent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1933065731185120080L;
	private String element;
	private String recommendation;
	private String type;
	private String color = "#FFFFFF";
	
	public DataContent(String element, String recommendation, String type) {
		this.element = element;
		this.recommendation = recommendation;
		this.type = type;
		this.color = "#FF0000";
	}
	
	
	
	public DataContent(String element, String type){
		this.element = element;
		this.recommendation = null;
		this.type = type;
		this.color = "#FFFFFF";
	}
	
	
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
	}
	public String getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "DataContent [element=" + element + ", recommendation="
				+ recommendation + ", type=" + type + "]";
	}
	
	
	
}
