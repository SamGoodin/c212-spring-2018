import java.util.ArrayList;
import java.util.Scanner;

public class LoginInterface {
	
	private ArrayList<Manager> managers;
	private ArrayList<Server> servers;
	//private Manager currentManager;
	//private Server currentServer;
	private Scanner sc;
	
	public LoginInterface(ArrayList<Manager> managers, ArrayList<Server> servers) {
		this.managers = managers;
		this.servers = servers;
		sc = new Scanner(System.in);
	}
	
	public EmployeeParent loginHelper(int userID, EmployeeParent employee) {
		System.out.println("Enter password for " + userID + ": ");
		if (sc.hasNextLine()) {
			String password = sc.nextLine();
			if (employee.getPassword().equals(password)) {
				//Correct password
				System.out.println("<>Successful login for {" + userID + "}<>");
				//sc.close();
				return employee;
			}
			else {
				//Incorrect password
				System.out.println("<>Incorrect Password for {" + userID + "}<>");
				//sc.close();
				return null;
			}
		}
		else {
			//Typed nothing and hit enter? TODO: test
			//sc.close();
			return null;
		}
	}
	
	public EmployeeParent login() {
		System.out.println("Enter User ID: ");
		if (sc.hasNextInt()) {
			int userID = sc.nextInt();
			sc.nextLine();
			//System.out.println("Enter password for " + userID + ": ");
			for (Server s : servers) {
				if (s.getUserID() == userID) {
					//Correct User ID, check Password
					return loginHelper(userID, s);
				}
			}
			for (Manager m : managers) {
				if (m.getUserID() == userID) {
					//Correct User ID, check password
					return loginHelper(userID, m);
				}
			}
			//If program gets here, means that user ID wasn't found
			System.out.println("<>Incorrect User ID {" + userID + "}<>");
			//sc.close();
			return null;
		}
		else {
			//Incorrect User ID (user ID must only contain numbers
			System.out.println("<>Incorrect User ID {" + sc.nextLine() + "}<>");
			//sc.close();
			return null;
		}
	}

}
