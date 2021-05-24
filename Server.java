import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Server extends EmployeeParent implements Employee
{
	private String Name;
	private int UserID;
	private String Password;
	private String Position;
	private double wage;
	private Restaurant restaurant;
	private ArrayList<Table> allTables;
	private Queue<Integer> tableIDs;
	
	public Server (String name, int userID, String password, String position, double wage)
	{
		super(name, userID, password, position, wage);
		this.Name = name;
		this.UserID = userID;
		this.Password = password;
		this.Position = position;
		this.wage = wage;
		this.allTables = new ArrayList<Table>();
		ArrayList<String> menuOptions = new ArrayList<String>();
		menuOptions.add("Change Password");
		menuOptions.add("Create Order");
		menuOptions.add("Add Table");
		menuOptions.add("Remove Table");
		menuOptions.add("Get Receipt");
		menuOptions.add("Pay Bill"); //tableID
		menuOptions.add("Change Order"); //newOrder
		menuOptions.add("Logout");
		super.setMenuOptions(menuOptions);
		tableIDs = new LinkedList<Integer>();
		for (int i = 0; i < 20; i++) {
			//Adds 20 ids to tableIDs (20 tables)
			tableIDs.add(i);
		}
	}
	
	public Queue<Integer> getTableIDs(){
		return this.tableIDs;
	}
	
	/*
	public ArrayList<String> displayMenuOptions() {
		ArrayList<String> validOptions = new ArrayList<String>();
		for (String st : menuOptions) {
			if (st.equals("Remove Table") || st.equals("Get Receipt") || st.equals("Pay Bill") ||
					st.equals("Change Order")) {
				if (allTables.size() > 0) {
					validOptions.add(st);
				}
			}
			else {
				validOptions.add(st);
			}
		}
		return menuOptions;
	}
	*/
	public void setPassword (String newPass)
	{
		this.Password = newPass;
		super.setPassword(newPass);
	}
	
	public void setRestaurant (Restaurant restaurant)
	{
		this.restaurant = restaurant;
		super.assignRestaurant(restaurant);
	}
	
	public Restaurant getRestaurant ()
	{
		return this.restaurant;
	}
	
	public String getName() {
		return this.Name;
	}
	
	public int getUserID() {
		return this.UserID;
	}
	
	public String getPassword() {
		return this.Password;
	}
	
	public double getWage() {
		return this.wage;
	}
	
	public String getPosition() {
		return this.Position;
	}
	
	
	public Table getTable (int tableID)
	{
		for (Table table : this.allTables)
		{
			if (table.getID() == tableID)
			{
				return table;
			}
		}
		//not found
		return null;
	}
	
	public void createTable(int numCustomers)
	{
		int tableID = tableIDs.remove();	//remove the table id from the queue, table in use
		Table newTable = new Table(tableID, numCustomers);
		this.allTables.add(newTable); //adds the table to the servers list and the restaurants list
		this.restaurant.addTable(newTable); // both tables have the same ID and should have the same properites
	}
	
	public String removeTable(int tableID) {
		for (Table table : this.allTables)
		{
			if (table.getID() == tableID)
			{
				if (table.getReceipt() != null) {
					if (table.getReceipt().isPaid()) {
						this.allTables.remove(table);
						this.restaurant.removeTable(table);
						tableIDs.add(tableID);	//Put the table back into the queue at the tail, usable again
						return "true";	//successfully removed table (id was found) & paid
					}
					else {
						return "not paid"; 
					}
				}
				else {
					return "not paid";
				}
			}
		}
		return "false";	//could not find table id
	}
	
	public ArrayList<Table> getAllTables() {
		return this.allTables;
	}
	
	
	// what should go here?
	public void orderFood(String entree, String side1, int side2) {
		
	}
		
	
}