import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeParent {
	
	private String name;
	private int userID;
	private String password;
	private String position;
	private double wage;
	private ArrayList<String> menuOptions;
	private Scanner sc;
	public Restaurant rest = null;
	
	public void clearScreen ()
	{
		for (int a = 0; a < 5; a++)
		{
			System.out.println("");
		}
		
	}
	
	public EmployeeParent(String name, int userID, String password, String position, double wage) {
		this.name = name;
		this.userID = userID;
		this.password = password;
		this.position = position;
		this.wage = wage;
		sc = new Scanner(System.in);
	}
	
	public void assignRestaurant (Restaurant rest)
	{
		this.rest = rest;
	}
	
	public ArrayList<String> getMenuOptions(){
		return this.menuOptions;
	}
	
	public void setMenuOptions(ArrayList<String> menuOptions) {
		this.menuOptions = menuOptions;
	}
	
	public boolean executeMenuOption(int choice, ArrayList<Server> servers, ArrayList<Manager> managers) throws FileNotFoundException {
		String menuChoice = menuOptions.get(choice);
		switch (menuChoice) {
		case "Change Password (Self)":	//Manager call
			clearScreen();
			System.out.println("Enter your manager ID: ");
			if (sc.hasNextInt()) {
				changePassword(sc.nextInt(), servers, managers);
				sc.nextLine();
				return false;
			}
			else {
				System.out.println("<>ID " + sc.nextLine() + " not found in system<>");
				return false;
			}
		case "Change Password (Server)":	//Manager call
			clearScreen();
			System.out.println("Enter the ID of the server that you want to change passwords: ");
			if (sc.hasNextInt()) {
				changePassword(sc.nextInt(), servers, managers);
				sc.nextLine();
				return false;
			}
			else {
				System.out.println("<>ID " + sc.nextLine() + " not found in system<>");
				return false;
			}
		case "Change Password":		//Server call
			clearScreen();
			System.out.println("Enter your server ID: ");
			if (sc.hasNextInt()) {
				int serverID = sc.nextInt();
				if (serverID == userID) {
					changePassword(serverID, servers, managers);
					sc.nextLine();
					return false;
				}
				else {
					//Other server trying to change this server password or vice versa
					System.out.println("You cannot change another employee's password!");
					return false;
				}
			}
			else {
				System.out.println("<>ID " + sc.nextLine() + " not found in system<>");
				return false;
			}
			
		case "Add Server":		//Server call
			clearScreen();
			System.out.println("Create a new server!");
			System.out.print("Name: ");
			String name = sc.nextLine();
			
			System.out.print("User ID: ");
			int newuserID = sc.nextInt();
			
			System.out.print("Password: ");
			String pass = sc.next();
			
			System.out.print("Wage: ");
			double wage = sc.nextDouble();
			
			Server server = new Server(name, newuserID, pass, "Server", wage);
			server.assignRestaurant(this.rest);
			servers.add(server);
			
			return true;
		
		case "Remove Server":		//Server call
			clearScreen();
			System.out.println("Remove a server!");
			
			System.out.print("Enter the servers User ID: ");
			int theuserID = sc.nextInt();
			
			for (Server theserver : servers)
			{
				if (theserver.getUserID() == theuserID)
				{
					servers.remove(theserver);
					break;
				}
			}
			
			System.out.println("The server has been removed!");
				
			return true;	
			
			
		case "Add Table":	//Manager & server call
			clearScreen();
			System.out.println("Enter the ID of the server who will have the new table: ");
			if (sc.hasNextInt()) {
				int serverID = sc.nextInt();
				boolean okay = true;
				
				if (okay) {
					addTable(serverID, servers);
					sc.nextLine();
					return false;
				}
				else {
					//Somehow got to this case, shouldn't though.
					System.out.println("Unexpected case addTable");
				}
					
			}
			else {
				System.out.println("<>ID " + sc.nextLine() + " not found in system<>");
				return false;
			}
		case "Create Order":	//Manager & server call
			clearScreen();
			System.out.println("Enter the table ID that you are creating an order for: ");
			if (sc.hasNextInt()) {
				int tableID = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the server ID that has the table: ");
				if (sc.hasNextInt()) {
					int serverID = sc.nextInt();
					sc.nextLine();
					if (serverID == userID) {
						boolean okay = true;
						if (okay) {
							addOrder(tableID, serverID, servers);
							return false;
						}
						else {
							//Somehow got to this case, shouldn't though.
							System.out.println("Unexpected case createOrder");
						}
					}
					else {
						//Other server trying to add order to table for this server or vice versa
						System.out.println("You cannot add orders for another server!");
						return false;
					}
				}
				else {
					System.out.println("<>Server ID " + sc.nextLine() + " not found in system<>");
					return false;
				}
			}
			else {
				System.out.println("<>Table ID " + sc.nextLine() + " not found in system<>");
				return false;
			}
		case "Remove Table":	//Manager & server call
			clearScreen();
			System.out.println("Enter the ID of the server who has the table you would like to remove: ");
			if (sc.hasNextInt()) {
				int serverID = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the table ID you would like to remove: ");
				if (sc.hasNextInt()) {
					int tableID = sc.nextInt();
					sc.nextLine();
					if (serverID == userID) {
						boolean okay = false;
						for (Manager m : managers) {
							//Managers can remove tables for any server
							if (userID == m.getUserID()) {
								okay = true;
								break;
							}
						}
						if (okay) {
							destroyTable(tableID, serverID, servers);
							return false;
						}
						else {
							//Somehow got to this case, shouldn't though.
							System.out.println("Unexpected case removeTable");
						}
					}
					else {
						//Other server trying to remove table for this server or vice versa
						System.out.println("You cannot remove tables for another server!");
						return false;
					}
				}
				else {
					System.out.println("<>Table ID " + sc.nextLine() + " not found in system<>");
					return false;
				}
			}
			else {
				System.out.println("<>ID " + sc.nextLine() + " not found in system<>");
				return false;
			}
		case "Get Receipt":		//Manager & server call
			clearScreen();
			System.out.println("Enter the ID of the server who has the table with the wanted receipt: ");
			if (sc.hasNextInt()) {
				int serverID = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the ID of the table that has the wanted receipt: ");
				if (sc.hasNextInt()) {
					int tableID = sc.nextInt();
					sc.nextLine();
					if (serverID == userID) {
						boolean okay = true;
						
						if (okay) {
							System.out.print("\n\n\n\n");
							getReceipt(tableID, serverID, servers);
							System.out.print("\n\n\n\n");
							return false;
						}
						else {
							//Somehow got to this case, shouldn't though.
							System.out.println("Unexpected case getReceipt");
						}
					}
					else {
						//Other server trying to get receipt for this server or vice versa
						System.out.println("You cannot get receipt for another server!");
						return false;
					}
				}
				else {
					System.out.println("<>Table ID " + sc.nextLine() + " not found in system<>");
					return false;
				}
			}
			else {
				System.out.println("<>Server ID " + sc.nextLine() + " not found in system<>");
				return false;
			}
		case "Pay Bill":	//Manager & server call
			clearScreen();
			System.out.println("Enter the ID of the server who has the table that is ready to pay: ");
			if (sc.hasNextInt()) {
				int serverID = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the ID of the table that is ready to pay: ");
				if (sc.hasNextInt()) {
					int tableID = sc.nextInt();
					sc.nextLine();
					boolean okay = true;
					
					if (okay) {
						payBill(tableID, serverID, servers);
						return false;
					}
					else {
						//Somehow got to this case, shouldn't though.
						System.out.println("Unexpected case payBill");
					}
				}
				else {
					System.out.println("<>Table ID " + sc.nextLine() + " not found in system<>");
					return false;
				}
			}
			else {
				System.out.println("<>Server ID " + sc.nextLine() + " not found in system<>");
				return false;
			}
		case "Change Order":	//Manager & server call
			clearScreen();
			System.out.println("Enter the ID of the server who has the table to change orders: ");
			if (sc.hasNextInt()) {
				int serverID = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the ID of the table to change orders: ");
				if (sc.hasNextInt()) {
					int tableID = sc.nextInt();
					sc.nextLine();
					boolean okay = false;
					for (Manager m : managers) {
						//Managers can change tables orders for any server
						if (userID == m.getUserID()) {
							okay = true;
							break;
						}
					}
					if (okay) {
						changeOrder(tableID, serverID, servers);
						return false;
					}
					else {
						//Somehow got to this case, shouldn't though.
						System.out.println("Unexpected case changeOrder");
					}
				}
				else {
					System.out.println("<>Table ID " + sc.nextLine() + " not found in system<>");
					return false;
				}
			}
			else {
				System.out.println("<>Server ID " + sc.nextLine() + " not found in system<>");
				return false;
			}
		case "Apply Discount":	//Manager call
			clearScreen();
			System.out.println("Enter the ID of the server who has the table to discount: ");
			if (sc.hasNextInt()) {
				int serverID = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the ID of the table to discount: ");
				if (sc.hasNextInt()) {
					int tableID = sc.nextInt();
					sc.nextLine();
					discountTable(tableID, serverID, servers);
					return false;
				}
				else {
					System.out.println("<>Table ID " + sc.nextLine() + " not found in system<>");
					return false;
				}
			}
			else {
				System.out.println("<>Server ID " + sc.nextLine() + " not found in system<>");
				return false;
			}
		case "Logout":
			clearScreen();
			System.out.println("<>Successfully logged out<>");
			
			
			String input = this.rest.getName().replaceAll("\\s+","");
			String fileLocation = input.toLowerCase() + ".txt"; // 
			
			this.rest.printData(new File(fileLocation));
			return true;
		default:
			return false;
		}
	}
	
	public void changeOrder(int tableID, int serverID, ArrayList<Server> servers) {
		Server employee = null;
		for (Server s : servers) {
			if (s.getUserID() == serverID) {
				employee = s;
				break;
			}
		}
		if (employee == null) {
			//ID not found
			System.out.println("<>Server ID " + serverID + " not found in system<>");
		}
		else {
			Table table = employee.getTable(tableID);
			if (table != null) {
				table.removeOrder(0);
				addOrder(tableID, serverID, servers);
			}
			else {
				//Table not found
				System.out.println("<>Table ID " + tableID + " not found in system. No payment made.<>");
			}
		}
	}
	
	public void discountTable(int tableID, int serverID, ArrayList<Server> servers) {
		Server employee = null;
		for (Server s : servers) {
			if (s.getUserID() == serverID) {
				employee = s;
				break;
			}
		}
		if (employee == null) {
			//ID not found
			System.out.println("<>Server ID " + serverID + " not found in system<>");
		}
		else {
			Table table = employee.getTable(tableID);
			if (table != null) {
				table.generateReceipt();
				table.getReceipt().discount(.15);
				table.getReceipt().printReceipt();
			}
			else {
				//Table not found
				System.out.println("<>Table ID " + tableID + " not found in system. No payment made.<>");
			}
		}
	}
	
	public void payBill(int tableID, int serverID, ArrayList<Server> servers) {
		Server employee = null;
		for (Server s : servers) {
			if (s.getUserID() == serverID) {
				employee = s;
				break;
			}
		}
		if (employee == null) {
			//ID not found
			System.out.println("<>Server ID " + serverID + " not found in system<>");
		}
		else {
			while (true) {
				Table table = employee.getTable(tableID);
				if (table != null) {
					table.generateReceipt();
					Receipt receipt = table.getReceipt();
					receipt.printReceipt();
					System.out.println("How much would you like to pay? (If you overpay, the extra amount will be assumed to be a gratuity)");
					if (sc.hasNextDouble()) {
						double paymentAmount = sc.nextDouble();
						sc.nextLine();
						if (paymentAmount > 0) {
							System.out.println("Would you like to leave a tip? If so, enter the amount. If no, enter -1");
							if (sc.hasNextDouble()) {
								double tip = sc.nextDouble();
								sc.nextLine();
								if (tip == -1 || tip > 0) {
									boolean paid = receipt.pay(paymentAmount, tip);
									if (paid) {
										System.out.println("Thank you for coming in and dining with us! Have a good day! (Paid)");
										break;
									}
									else {
										System.out.println("The amount you entered does not equal the total balance.");
										break;
									}
								}
								else {
									System.out.println("<>Enter a valid number<>");
									break;
								}
							}
							else {
								System.out.println("<>Enter a valid number<>");
								break;
							}
						}
						else {
							System.out.println("<>Enter a valid number<>");
							break;
						}
					}
					else {
						System.out.println("<>Enter a valid number<>");
						break;
					}
				}
				else {
					//Table not found
					System.out.println("<>Table ID " + tableID + " not found in system. No payment made.<>");
					break;
				}
			}
		}
	}
	
	public void getReceipt(int tableID, int serverID, ArrayList<Server> servers) {
		Server employee = null;
		for (Server s : servers) {
			if (s.getUserID() == serverID) {
				employee = s;
				break;
			}
		}
		if (employee == null) {
			//ID not found
			System.out.println("<>Server ID " + serverID + " not found in system<>");
		}
		else {
			while (true) {
				Table table = employee.getTable(tableID);
				if (table != null) {
					table.generateReceipt();
					table.getReceipt().printReceipt();
					break;
				}
				else {
					//Table not found
					System.out.println("<>Table ID " + tableID + " not found in system. No receipt found.<>");
					break;
				}
			}
		}
	}
	
	public void addOrder(int tableID, int serverID, ArrayList<Server> servers) {
		Server employee = null;
		for (Server s : servers) {
			if (s.getUserID() == serverID) {
				employee = s;
				break;
			}
		}
		if (employee == null) {
			//ID not found
			System.out.println("<>Server ID " + serverID + " not found in system<>");
		}
		else {
			while (true) {
				Table table = employee.getTable(tableID);
				if (table != null) {
					Menu menu = employee.getRestaurant().getMenu();
					for (int i = 0; i < menu.allItems.size(); i++) {
						System.out.println(i + ") " + menu.allItems.get(i).getName() + " $" + menu.allItems.get(i).getPrice());
					}
					Order order = new Order(menu);
					while (true) {
						System.out.println("Enter what option you would like to order from the menu above: ");
						if (sc.hasNextInt()) {
							int choice = sc.nextInt();
							Item item = menu.getItem(choice);
							if (item != null) {
								order.addItem(item);
								for (Item orderedItem : order.getOrderItems()) {
									if (order.getOrderItems().indexOf(orderedItem) == order.getOrderItems().size() - 1) {
										System.out.println(orderedItem.toString());
									}
									else {
										System.out.print(orderedItem.toString() + ", ");
									}
								}
								int moreItems = -1;
								while (moreItems != 0 && moreItems != 1) {
									System.out.println("If you would like to add more items to the order, enter 1. If not, enter 0.");
									if (sc.hasNextInt()) {
										moreItems = sc.nextInt();
										sc.nextLine();
									}
									else {
										System.out.println("<>Enter a valid number<>");
									}
								}
								if (moreItems == 0) {
									break;
								}
							}
							else {
								System.out.println("<>Enter a valid number<>");
							}
						}
						else {
							System.out.println("<>Enter a valid number<>");
						}
					}
					table.addOrder(order);
					System.out.println("Order successfully added.");
					break;
				}
				else {
					//Table not found
					System.out.println("<>Table ID " + tableID + " not found in system. No order added.<>");
					break;
				}
			}
		}
	}
	
	public void addTable(int serverID, ArrayList<Server> servers) {
		Server employee = null;
		for (Server s : servers) {
			if (s.getUserID() == serverID) {
				employee = s;
				break;
			}
		}
		if (employee == null) {
			//ID not found
			System.out.println("<>Server ID " + serverID + " not found in system<>");
		}
		else {
			while (true) {
				System.out.println("How many guests will be at table [tableID=" + employee.getTableIDs().peek() + "]? ");
				if (sc.hasNextInt()) {
					int num = sc.nextInt();
					if (num > 0) {
						employee.createTable(num);
						break;
					}
					else {
						System.out.println("<>Enter a valid number<>");
					}
				}
				else {
					System.out.println("<>Enter a valid number<>");
				}
			}
		}
	}
	
	public void destroyTable(int tableID, int serverID, ArrayList<Server> servers) {
		Server employee = null;
		for (Server s : servers) {
			if (s.getUserID() == serverID) {
				employee = s;
				break;
			}
		}
		if (employee == null) {
			//ID not found
			System.out.println("<>Server ID " + serverID + " not found in system<>");
		}
		else {
			String idFound = employee.removeTable(tableID);
			if (idFound.equals("false")) {
				System.out.println("<>Table ID " + tableID + " not found in system. No table removed.<>");
			}
			else if (idFound.equals("not paid")) {
				System.out.println("<>Table ID " + tableID + " hasn't paid yet. No table removed.<>");
			}
			else {
				System.out.println("<>Table ID " + tableID + " successfully removed and is now available to use again.<>");
			}
		}
		
	}
	
	public void changePassword(int id, ArrayList<Server> servers, ArrayList<Manager> managers) {
		EmployeeParent employee = null;
		for (Server s : servers) {
			if (s.getUserID() == id) {
				employee = s;
				break;
			}
		}
		if (employee == null) {
			for (Manager m : managers) {
				if (m.getUserID() == id) {
					employee = m;
					break;
				}
			}
		}
		if (employee == null) {
			//ID to change password not found
			System.out.println("<>ID " + sc.nextLine() + " not found in system<>");
		}
		else {
			System.out.println("Enter the new password for {" + id + "}: ");
			sc.nextLine();
			String newPassword = sc.nextLine();
			System.out.println("Press Enter to confirm.");
			//sc.nextLine();
			employee.setPassword(newPassword);
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getUserID() {
		return this.userID;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getPosition() {
		return this.position;
	}
	
	public double getWage() {
		return this.wage;
	}
	
	public void displayMenuOptions() {
		for (int i = 0; i < menuOptions.size(); i++) {
			System.out.println(i + ") " + menuOptions.get(i));
		}
	}
	
	

}
