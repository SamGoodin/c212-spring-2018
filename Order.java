import java.util.ArrayList;

public class Order{
	
	private ArrayList<Item> orderItems = new ArrayList<Item>();
	private int orderNumber;
	private Menu menu;
	
	public Order(String entree, String side1, String side2, Menu menu) {

		this.orderItems.add(menu.getItem(entree)); // we need to link these with the menu prices
		this.orderItems.add(menu.getItem(side1));
		this.orderItems.add(menu.getItem(side2));
		this.menu = menu;
		//this.orderItems.remove(menu.getItem(entree));
	}
	
	public Order(Menu menu) {
		this.menu = menu;
	}
	
	public void addItem(Item item) {
		this.orderItems.add(item);
	}
	
	public void addItem(String item) 
	{
		this.orderItems.add(menu.getItem(item));
	}
	
	// why would we need this?
	public Item getItem(String item) {
		return null;
	}
	
	public int getOrderNumber ()
	{
		return this.orderNumber;
	}
	
	public ArrayList<Item> getOrderItems ()
	{
		return this.orderItems;
	}
	
	public void removeItem(String item) 
	{	
		boolean viable = false;
		int idx = 0;
		for (Item someItem : this.orderItems)
		{
			if (someItem.getName().equals(item))
			{
				//valid item to remove
				viable = true;
				idx = this.orderItems.indexOf(someItem);
			}
		} // end for
		if (viable) {
			this.orderItems.remove(idx);
		}
		
	}	
	
}