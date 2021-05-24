
public class Item {
	
	private double price;
	private String name;
	
	public Item(String nameOfItem, double price)
	{
		this.name = nameOfItem;
		this.price = price;
	}
	
	public double getPrice ()
	{
		return this.price;
	}
	
	public String getName ()
	{
		return this.name;
	}
	
	public String toString() {
		return this.name + " $" + this.price;
	}
	

}
