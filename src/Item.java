
public class Item {
	String id;
	String title;
	String inStock;
	String cost;
	double discount;
	int quantity;
	double total;
	
	public Item(String id, String title, String inStock, String cost, double discount, int quantity, double total) {
		this.id = id;
		this.title = title;
		this.inStock = inStock;
		this.cost = cost;
		this.discount = discount;
		this.quantity = quantity;
		this.total = total;
	}
}
