public static void getMerchandiseStock(storeID, productID)
{

	System.out.println("Getting total merchandise stock for the entered storeID and productID...");
	System.out.println();

	try
	{
		stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT SUM(Quantity) AS Total_Stock FROM InventoryInfo WHERE productID = "+productID+" AND storeID = "+storeID+" ");
		
		while(rs.next())
		{
			Integer integer = rs.getInt("Total_Stock");

			if(integer != null)
			{
				total_stock = integer.intValue();
				System.out.println("The total stock for the entered storeID: "+storeID+" and productID: "+productID+"  is: "+total_sales);

			}	
			else
			{
				System.out.println("The entered storeID or productID doesnt exist..please try again");
			}
		}

	}
	catch(Throwable oops)
	{
		oops.printStackTrace();
	}
}

public static void getMerchandiseStock_product(productID)
{

	System.out.println("Getting total merchandise stock for the entered productID...");
	System.out.println();

	try
	{
		stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT SUM(Quantity) AS Total_Stock FROM InventoryInfo WHERE productID = "+ProductID+" ");
		
		while(rs.next())
		{
			Integer integer = rs.getInt("Total_Stock");

			if(integer != null)
			{
				total_stock = integer.intValue();
				System.out.println("The total stock for the entered productID: "+productID+"  is: "+total_sales+" items.");

			}	
			else
			{
				System.out.println("The entered productID doesnt exist..please try again");
			}
		}

	}
	catch(Throwable oops)
	{
		oops.printStackTrace();
	}
}


public static void getMerchandiseStock_store(storeID)
{

	System.out.println("Getting total merchandise stock for the entered storeID...");
	System.out.println();

	try
	{
		stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT SUM(Quantity) AS Total_Stock FROM InventoryInfo WHERE storeID = "+storeID+" ");
		
		while(rs.next())
		{
			Integer integer = rs.getInt("Total_Stock");

			if(integer != null)
			{
				total_stock = integer.intValue();
				System.out.println("The total stock for the entered storeID: "+storeID+"  is: "+total_sales+" items.");

			}	
			else
			{
				System.out.println("The entered storeID doesnt exist..please try again");
			}
		}
		stmt.close();
		conn.close();
	}
	catch(Throwable oops)
	{
		oops.printStackTrace();
	}
}