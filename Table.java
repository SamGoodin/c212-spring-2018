import java.util.ArrayList;

public class Table {
	
	private int tableID;
	private int numberOfCustomers;
	private int numberOfOrders = 0;
	private ArrayList<Order> tableOrders;
	
	private Receipt receipt; // total receipt for the table
	private ArrayList<Item> menu; // ? do we need this ?
	
	public Table (int tableID, int numberOfCustomers)
	{
		this.tableID = tableID;
		this.numberOfCustomers = numberOfCustomers;
		this.tableOrders = new ArrayList<Order>();
		System.out.println(toString());
	}
	
	// do we need an add customer / remove customer? If so - we need a customer class
	
	public void addOrder (Order order)
	{
		this.tableOrders.add(order); // adds the order to the table orders
		this.numberOfOrders ++;
	}
	
	public Order getOrder() {
		return tableOrders.get(0);
	}
	
	public void removeOrder (int orderIndex)
	{
		this.tableOrders.remove(orderIndex);
	}
	
	public int getID ()
	{
		return this.tableID;
	}
	
	public void generateReceipt () // creates the receipt ... doesn't return it 
	{
		if (receipt != null) {
			this.receipt.reappendOrders(this.tableOrders);
		}
		else {
			this.receipt = new Receipt(this.tableOrders);
		}
	}
	
	public Receipt getReceipt ()
	{
		return this.receipt;
	}
	
	public String toString() {
		return "Table " + this.tableID + " [" + this.numberOfCustomers + " customers]";
	}
	
	
}