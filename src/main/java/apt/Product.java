package apt;

public class Product {
	private String barcode;
	private double cost;
	private String name;
	
	public Product(String barcode, double cost, String name) {
		this.barcode = barcode;
		this.cost = cost;
		this.name = name;
	}
	
	public String getBarcode() {
		return barcode;
	}
	
	public double getCost() {
		return cost;
	}
	
	public String getName() {
		return name;
	}
}
