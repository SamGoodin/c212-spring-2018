
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant {

	private ArrayList<Manager> managers;
	private ArrayList<Server> servers;
	private String restaurantName;
	private ArrayList<Table> tables = new ArrayList<Table>();
	private Menu menu = null;
	private EmployeeParent employee;
	//private Server currentServer;
	//private Manager currentManager;
	private Scanner sc = new Scanner(System.in);
	private String storageFileName;
	
	
private File storageFile;
	
	// load the data into the arrays
	public void loadData (File file) throws FileNotFoundException
	{
		Scanner in = new Scanner(file);
		this.storageFile = file; // the file that the information is stored on
		
		this.servers = new ArrayList<Server>();
		this.managers = new ArrayList<Manager>();
		
		this.restaurantName = in.nextLine(); // name of the rest
		this.menu = new Menu(in.nextLine());
		
		while (in.hasNextLine())
		{
			try 
			{
				System.out.print("-");
				String name = in.next();
				int userId = in.nextInt();
				String password = in.next();
				String employeeType = in.next(); //position
				double wage = in.nextDouble();
				
				if (employeeType.equals("Manager"))
				{
					Manager manager = new Manager(name, userId, password, employeeType, wage); 
					manager.setRestaurant(this);
					this.managers.add(manager);
				}
				else if (employeeType.equals("Server"))
				{
					Server server = new Server(name, userId, password, employeeType, wage);
					server.setRestaurant(this);
					this.servers.add(server);
				}	
			}
			catch(Exception e)
			{
				
			}
		} // end loop
		
		in.close();
	}
	
	//prints the data to the text file (should be done on logout)
	public void printData (File fileName) throws FileNotFoundException
	{
		PrintWriter pw = new PrintWriter(fileName);
		
		pw.write(this.restaurantName + "\r\n");
		pw.write(this.menu.getName() + "\r\n");
		
		for (Manager manager : this.managers)
		{
			pw.write(manager.getName() + " " + manager.getUserID() + " " + manager.getPassword() + " " + manager.getPosition() + " " + manager.getWage() + "\r\n");
		}
		
		
		for (Server server : this.servers)
		{
			pw.write(server.getName() + " " + server.getUserID() + " " + server.getPassword() + " " + server.getPosition() + " " + server.getWage() + "\r\n");
		}
		
		pw.close();
		
		
	}
	
	public Restaurant (String loadFileName) throws FileNotFoundException
	{
		this.loadData(new File(loadFileName));
	}
	
	
	// only one restaurant driver per restaurant
	public Restaurant (String restaurantName, ArrayList<Manager> managers, ArrayList<Server> servers, ArrayList<Table> tables) throws FileNotFoundException
	{
		// writes to the file
		
		
		// creates the restaurant system with the servers and managers
		this.managers = managers;
		this.servers = servers;
		this.tables = new ArrayList<Table>();
		
		//assigns the managers to this restuarant
		for (Manager manager : managers)
		{
			manager.setRestaurant(this);
		}
		
		for (Server server : servers)
		{
			server.setRestaurant(this);
		}
		
		
		this.restaurantName = restaurantName;
		
		if (tables == null)
		{
			this.tables = new ArrayList<Table>();
		}
		else
		{
			this.tables = tables; // tables in the restaurant		
		}
	}
	
	// location to the menu on the system
	public void assignMenu (String location) throws FileNotFoundException
	{
		this.menu = new Menu(location);
	}
	
	public Menu getMenu ()
	{
		return this.menu;
	}
	
	public String getName ()
	{
		return this.restaurantName;
	}
	
	// adds a table to the rest
	public void addTable (Table table)
	{
		this.tables.add(table);
	}
	
	// removes a table from the rest
	public void removeTable (Table table)
	{
		this.tables.remove(table);
	}
	
	public void addServer (Server server)
	{
		this.servers.add(server);
	}
	
	public void addManager (Manager manager)
	{
		this.managers.add(manager);
		manager.setRestaurant(this);
	}
	
	public void removeServer (Server server)
	{
		this.servers.remove(server);
	}
	
	public Table getTable (int tableID)
	{
		for (Table table : this.tables)
		{
			if (table.getID() == tableID)
			{
				return table;
			}
		}
		
		return null;
	}
	
	public EmployeeParent getLoggedInEmployee() {
		return this.employee;
	}
	
	
	//gets the managers 
	public ArrayList<Manager> getManagers ()
	{
		return this.managers;
	}
	
	//gets the servers
	public ArrayList<Server> getServers ()
	{
		return this.servers;
	}
	
	public EmployeeParent login() {
		LoginInterface login = new LoginInterface(managers, servers);
		while (true) {
			employee = login.login();
			if (employee != null) {
				return employee;
			}
		}
	}
	
	public boolean doMenuOptions(EmployeeParent employee) throws FileNotFoundException {
		//Return false if not logging out, true if logging out
		if (sc.hasNextInt()) {
			int choice = sc.nextInt();
			sc.nextLine();
			return employee.executeMenuOption(choice, servers, managers);
			
		}
		else {
			System.out.println("<>Enter a valid number<>");
			return false;
		}
	}
	
	
	
	
	
	
}
