# C212-Spring-2018
Final Project Repository
Directions

Server
Step 1: Enter the System
Login to system - two options - create a restaurant or enter “taco bell”. If you create a restaurant follow prompts and then it will be the same. For the purposes of testing enter “taco bell”. Enter a user ID (Manager user ID = 1, password = password) or (Server ID = 2, password = jakespassword) already loaded in system or make new user. For testing purposes and to add servers go into manager portal using manager user ID and password
Server
Step 2: After successful login choose from options 0-7
Option 0 - Change Password: Enter user ID, Enter new Password, Press enter
Option 1 - Create Order: Enter table ID, enter server ID, Choose food/drink item number, select 1 to add another item or 0 if not
Option 2 - Add Table: Enter server ID of the new table, enter how many guests at the table
Option 3 - Remove Table: Enter server ID, enter table ID, if outstanding balance on table they can not be removed, else they will be removed
Option 4 - Get Receipt: Enter server ID, Enter table ID, Receipt will print
Option 5 - Pay Bill: Enter server ID, Enter table ID, Enter payment type, if card - enter number and pin, enter how much you want to pay, enter tip, Meal completed
Option 6 - Change Order: (Must have order already in for table) Enter Server ID, enter table ID, enter food option #
Option 7 - Logout:  Successfully logged out

Manager
Step 1: Enter the System
Login to system - can use any ID and password in system. For testing purposes use ID = 1, Password = password123
Step 2: After successful login choose from options 0-9
Option 0 - Change Password (Self): Enter manager ID, Enter new password, press enter
Option 1 - Change Password (Server): Enter server ID whose password you wish to change, enter new password, press enter
Option 2 - Add Server: Enter a name (String), enter user ID (1, and 2 are taken, enter 3), enter password, Wage
Option 3 - Remove Server: Enter ID of server you’d like to remove, Server has been removed
Option 4 - Add Table: Enter server ID of the new table, enter how many guests at the table
Option 5 - Create Order: Enter table ID, enter server ID, Choose food/drink item number, select 1 to add another item or 0 if not
Option 6 - Remove Table:  Enter server ID, enter table ID, if outstanding balance on table they can not be removed, else they will be removed
Option 7 - Get Receipt: Enter server ID, Enter table ID, Receipt will print
Option 8 - Pay Bill: Enter server ID, Enter table ID, Enter payment type, if card - enter number and pin, enter how much you want to pay, enter tip, Meal completed
Option 9 - Change Order:  (Must have order already in for table) Enter Server ID, enter table ID, enter food option #
Option 10 - Apply Discount: Enter the ID of the server who has the table to discount (must have a table in the system), enter the ID of the Table, Prints receipt with 15% discount
Option 11 - Logout: Successfully logged out
