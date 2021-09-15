public static void AddNewInventory()
{
	System.out.println("Enter the storeID: ");
	int storeID = sc.nextInt();
	System.out.println();

	System.out.println("Enter the productName: ");
	String productName = sc.next();
	System.out.println();

	System.out.println("Enter the productionDate (in YYYY-MM-DD format): ");
	String productionDate = sc.next();
	System.out.println();

	System.out.println("Enter the expirationDate (in YYYY-MM-DD format): ");
	String expirationDate = sc.next();
	System.out.println();

	System.out.println("Enter the newly arrived items quantity: ");
	int quantity = sc.nextInt();
	System.out.println();

	System.out.println("Enter the buyPrice: ");
	int buyPrice = sc.nextInt();
	System.out.println();

	System.out.println("Enter the marketPrice: ");
	int marketPrice = sc.nextInt();
	System.out.println();

	System.out.println("Adding new arrivals to the inventory...");
	System.out.println();

	int productID;

	try
	{
		stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT productID FROM productTable WHERE productName = "+productName+" AND buyPrice = "+buyPrice+" AND marketPrice = "+marketPrice);
		
		while(rs.next())
		{
			Integer integer = rs.getInt("productID");

			if(integer != null)
			{
				productID = integer.intValue();

			}	
			else
			{
				stmt.executeUpdate("INSERT into productTable(productName, buyPrice, marketPrice) VALUES(\'"+productName+"\',"+ buyPrice+","+ marketPrice+")");
				rs = stmt.executeQuery("SELECT productID FROM productTable WHERE productName = "+productName+" AND buyPrice = "+buyPrice+" AND marketPrice = "+marketPrice);
				while(rs.next())
				{
					integer = rs.getInt("productID");
					productID = integer.intValue();
				}
			}
		}

		rs = stmt.executeQuery("SELECT * FROM InventoryInfo WHERE productID = "+productID+" AND storeID = "+storeID);
		
		while(rs.next())
		{
			Integer integer = rs.getInt("quantity");

			if(integer != null)
			{
				int old_quantity = integer.intValue();
				int updated_quantity = old_quantity + quantity;
				stmt.executeUpdate("UPDATE InventoryInfo SET quantity = "+updated_quantity+"\' WHERE productID = "+productID+" AND storeID = "+storeID);
				stmt.executeUpdate("UPDATE InventoryInfo SET productionDate = \'"+productionDate+"\' WHERE productID = "+productID+" AND storeID = "+storeID);
				stmt.executeUpdate("UPDATE InventoryInfo SET expirationDate = \'"+expirationDate+"\' WHERE productID = "+productID+" AND storeID = "+storeID);

			}	
			else
			{
				stmt.executeUpdate("INSERT into InventoryInfo(productID, storeID, productionDate, expirationDate, quantity) VALUES("+productID+","+storeID+",\'"+productionDate+"\',\'"+expirationDate+"\',"+quantity+")");
			}
		}

		System.out.println("Successfully added newly arrived inventory.");

	}
	catch(Throwable oops)
	{
		oops.printStackTrace();
	}
}