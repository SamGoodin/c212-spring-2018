import java.util.ArrayList;

public class Manager extends EmployeeParent implements Employee {
	private String Name;
	private int UserID;
	private String Password;
	private String Position;
	private double wage;
	private Restaurant restaurant = null; // the current restaurant
	//private ArrayList<String> menuOptions;
	private double discount = .15;
	
	
	public Manager (String name, int userID, String password, String position, double wage)
	{
		super(name, userID, password, position, wage);
		this.Name = name;
		this.UserID = userID;
		this.Password = password;
		this.Position = position;
		this.wage = wage;
		ArrayList<String> menuOptions = new ArrayList<String>();
		menuOptions.add("Change Password (Self)");
		menuOptions.add("Change Password (Server)");
		menuOptions.add("Add Server");
		menuOptions.add("Remove Server");
		menuOptions.add("Add Table");
		menuOptions.add("Create Order");
		menuOptions.add("Remove Table");
		menuOptions.add("Get Receipt");
		menuOptions.add("Pay Bill"); //tableID
		menuOptions.add("Change Order"); //newOrder
		menuOptions.add("Apply Discount");
		menuOptions.add("Logout");
		super.setMenuOptions(menuOptions);
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
	
	public void setPassword(String password) {
		this.Password = password;
		super.setPassword(password);
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
	
	// returns a table that the user has
	public Table getTable(int tableID) {
		return this.restaurant.getTable(tableID);
	}
	
	public void createTable(int tableID, int numCustomers) {
		this.restaurant.addTable(new Table(tableID, numCustomers)); // creates a new table
	}
	
	public void removeTable(Table table) {
		this.restaurant.removeTable(table);
	}
	
	// returns an arraylist of tables 
	public ArrayList<Table> getAllTables() {
		return null;
	}
	
	/*// what should go here? same issue w server idk???
	public void orderFood(int itemID, int sideID, int sideID2) {
		
	}
	*/
	
	// give the manager the right to add a new server to the staff
	private void addServer(String name, int userID, String password, double wage, String position) {
		this.restaurant.addServer(new Server(name, userID, password, position, wage));
	}
	
	// gives the manager the right to remove a server from the staff
	private void removeServer(int userID) {
		for (Server server : this.restaurant.getServers())
		{
			if (server.getUserID() == userID)
			{	// removes the server from the restaurant
				this.restaurant.removeServer(server);
			}
		}
	}
	

	// do we need this? IF yes, should we include a percentage? Dollar amnt? etc.
	private void discountTable(int tableID) {
		
	}
	
	// should this be changing a servers password??? YES? Ok
	private void changePassword(int UserID, String newPassword) 
	{
		for (Server server : this.restaurant.getServers())
		{
			if (server.getUserID() == UserID)
			{
				server.setPassword(newPassword);
			}
		}
	}
	
}