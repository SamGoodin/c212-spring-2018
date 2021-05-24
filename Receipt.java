import java.util.ArrayList;
import java.util.Queue;

public class Receipt{
	
	private ArrayList<Order> allOrders;
	private double total = 0;
	private static int receiptID = 0;
	private boolean paid = false;
	
	public Receipt (ArrayList<Order> tableOrders)
	{
		this.allOrders = tableOrders;
		this.total = 0;
		
		// gets the total of the order
		for (Order order : tableOrders)
		{
			for (Item item : order.getOrderItems())
			{
				this.total += item.getPrice();
			}
		}
		receiptID += 1;
		
	}
	
	public boolean pay(double amount, double tip) {
		if (amount >= total) {
			paid = true;
			return true;
		}
		else {
			return false;
		}
	}
	
	public double getTotal ()
	{
		return this.total;
	}
	
	public void paid() {
		paid = true;
	}
	
	public boolean isPaid() {
		return paid;
	}
	
	public int getOrderID ()
	{
		return this.receiptID;
	}
	
	public void discount(double rate) {
		double discount = total * rate;
		this.total -= discount;
		for (Order order : allOrders) {
			order.addItem(new Item("Discount (" + (rate * 100) + "%)", rate * -1));
		}
	}
	
	public void reappendOrders(ArrayList<Order> tableOrders) {
		this.allOrders = tableOrders;
		this.total = 0;
		
		// gets the total of the order
		for (Order order : tableOrders)
		{
			for (Item item : order.getOrderItems())
			{
				this.total += item.getPrice();
			}
		}
		this.total = (this.total * 100.0) / 100.0;	//rounds to 2nd decimal place
	}
	
	public void printReceipt ()
	{
		for (Order order : this.allOrders)
		{
			for (Item item : order.getOrderItems())
			{
				System.out.println("Item: " + item.getName() + " Price: " + item.getPrice());
			}
		}
		System.out.println("Total: " + this.total + ", ID: " + this.receiptID);
		
		
		
	}
	
	
}