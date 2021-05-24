import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Menu{
	// pull in menu items from menu.txt
	public ArrayList<Item> allItems = new ArrayList<Item>();
	public ArrayList<Item> entrees;
	public ArrayList<Item> sides;
	public ArrayList<Item> drinks;
	
	private String menuLocation;
	
	public String getName ()
	{
		return this.menuLocation;
	}
	
	// location to the menu on the system
	public Menu (String menuLocation) throws FileNotFoundException
	{
		this.menuLocation = menuLocation;
		
		try 
		{
			Scanner in = new Scanner (new File(menuLocation)); // creates a new scanner for the reading of the menu
			
			// menu format
			// itemName --- itemType --- itemPrice
			
			while (in.hasNextLine()) 
			{
				String line = in.nextLine();
				
				String [] data = line.split(",");
				String name = data[0];
				String itemType = data[1];
				double itemPrice = Double.parseDouble(data[2]);
				
				
				this.allItems.add(new Item(name, itemPrice));
				
				
				if (itemType.equals("Drink"))
				{
					this.drinks.add(new Item(name, itemPrice));
				}
				else if (itemType.equals("Side"))
				{
					this.sides.add(new Item(name, itemPrice));
				}
				else if (itemType.equals("Entree"))
				{
					this.entrees.add(new Item(name, itemPrice));
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error! No menu was found!");
		}
		
		
		
		
		
	}
	
	public Item getItem(int index) {
		if (index < allItems.size() && index > -1) {
			return allItems.get(index);
		}
		else {
			return null;
		}
	}
	
	public Item getItem (String itemName)
	{
		for (Item item : allItems)
		{
			if (item.getName().equals(itemName))
			{
				return item;
			}
			
			
		} // end for
		
		
		return null;
		
	}
	
}