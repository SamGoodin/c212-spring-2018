import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RestaurantRunner {
	
	
	public static void main (String[] args) throws FileNotFoundException
	{
		
		Scanner in = new Scanner(System.in);
		
		Restaurant restaurant;
		
		System.out.print("Enter your Restaurant Name to load or...\n");
		System.out.print("Enter + to start a new restaurant system: ");
		String input = in.nextLine();
		
		if (input.equals("+"))
		{
			// new restaurant
			System.out.print("Enter your new restaurant name: ");
			String newRestName = in.nextLine();
		
			restaurant = new Restaurant(newRestName, new ArrayList<Manager>(), new ArrayList<Server>(), new ArrayList<Table>());
			
			System.out.print("Enter the location to your menu: ");
			String menuLoc = in.nextLine();
			restaurant.assignMenu(menuLoc);
			
			// writes the restaurant to a file location
			String fileLoc = newRestName.replaceAll("\\s+","");
			fileLoc = fileLoc.toLowerCase() + ".txt";
			restaurant.printData(new File(fileLoc));

			System.out.println("Congrats on your new restaurant!");
			
			
			
			System.out.println("Create a manager!");
			System.out.print("Name: ");
			String name = in.nextLine();
			
			System.out.print("User ID: ");
			int userID = in.nextInt();
			
			System.out.print("Password: ");
			String password = in.next();
			
			System.out.print("Wage: ");
			double wage = in.nextDouble();
			
			Manager startingManager = new Manager(name, userID, password, "Manager", wage); 
			restaurant.addManager(startingManager);
			startingManager.assignRestaurant(restaurant);
			
			input = restaurant.getName().replaceAll("\\s+","");
			String fileLocation = input.toLowerCase() + ".txt"; //  
			
			restaurant.printData(new File(fileLocation));
		}
		else 
		{
			input = input.replaceAll("\\s+","");
			String fileLocation = input.toLowerCase() + ".txt"; //  
			
			restaurant = new Restaurant(fileLocation);
		}
		
		
		
		while (true) {
			//-------------------------LOGIN------------------------------------------
			EmployeeParent loggedInEmployee;
			while (true) {
				loggedInEmployee = restaurant.login();
				if (loggedInEmployee != null) {
					break;
				}
			}
			//-------------------------SERVER/MANAGER MENU--------------------------------------
			Scanner sc = new Scanner(System.in);
			
			while (true) {
				for (int i = 0; i < loggedInEmployee.getMenuOptions().size(); i++) {
					System.out.println(i + ") " + loggedInEmployee.getMenuOptions().get(i));
				}
				boolean choice = restaurant.doMenuOptions(loggedInEmployee);
				if (choice) {
					//logout
					break;
				}
			}
		}
		
		/*
		//Employee em = new Employee();
		ArrayList<String> menuOptions = currentServer.displayMenuOptions();
		int i = 0;
		while (true) {
			System.out.println("<>Enter the number that corresponds with an option<>");
			for (String s : menuOptions) {
				System.out.println(i + ") " + s);
				i += 1;
			}
			if (sc.hasNextInt()) {
				int option = sc.nextInt();
			}
			break;
		}
		
		
		int numOfGuests = 0;
		while (true) {
			System.out.println("How many guests do you have?");
			if (sc.hasNextInt()) {
				numOfGuests = sc.nextInt();
				sc.nextLine();
				break;
			}
			else {
				sc.nextLine();
			}
		}
				
		ArrayList<Table> tables = new ArrayList<Table>();
		// ten people
		tables.add(new Table(1, numOfGuests)); // adds a new table to the restaurant
		
		// name managers servers tables
		Restaurant bobEvans = new Restaurant ("Bob Evans", managers, servers, tables); // tables = null (brand new restaurant)
		bobEvans.assignMenu("menu.txt");

		
		// print a receipt
		
		Manager managerBilly = bobEvans.getManagers().get(0);
		numOfGuests = 0;
		while (true) {
			System.out.println("How many guests do you have?");
			if (sc.hasNextInt()) {
				numOfGuests = sc.nextInt();
				sc.nextLine();
				break;
			}
			else {
				sc.nextLine();
			}
		}
		managerBilly.createTable(2, numOfGuests); // manager billy creates a new table
		
		Server james = bobEvans.getServers().get(0); // gets James
		
		Order customer1Order = new Order("Burger", "French Fries", "Water", bobEvans.getMenu());
		customer1Order.addItem("Soda");
		Order customer2Order = new Order("Hot Dog", "French Fries", "Beer", bobEvans.getMenu());
		customer1Order.addItem("Spaghetti");
		customer1Order.removeItem("Spaghetti");
		
		managerBilly.getTable(2).addOrder(customer1Order);
		managerBilly.getTable(2).addOrder(customer2Order);

		managerBilly.getTable(2).generateReceipt();
		Receipt tableReceipt = managerBilly.getTable(2).getReceipt();
		tableReceipt.printReceipt();
		
		
		
		
		
		
		*/
		//System.out.println(bobEvans.getManagers().get(0).getName()); // first managers name
		//System.out.println(bobEvans.getManagers().get(0).getRestaurant().getName()); // first managers name
	}

}
