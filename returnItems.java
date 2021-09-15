public static void returnItems()
{
	System.out.print("Enter the trasactionID: ");
	int trasactionID = sc.nextInt();
	System.out.println();

	System.out.print("Enter the productID: ");
	String productID = sc.next();
	System.out.println();

	System.out.print("Enter the returns quantity: ");
	int return_quantity = sc.nextInt();
	System.out.println();

	System.out.print("Returning Items....");
	System.out.println();

	int storeID = 0;
	double initial_total_price = 0;
	double new_total_price = 0;
	double price_diff = 0;
	int old_quantity = 0;
	int updated_quantity = 0;
	double prod_initial_final_price = 0;
	double prod_new_final_price = 0;
	int old_quantity_inventory = 0;
	int new_quantity_inventory = 0;

	try{

		stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT TotalPrice, storeID FROM Transactions WHERE trasactionID = " + trasactionID);
		

		if(rs.next()){
			storeID = rs.getInt("storeID");
			initial_total_price = rs.getInt("TotalPrice");
		}else{
			System.out.println("Invalid TransactionID entered.");
			returnItems();
			return;
		}


		rs = stmt.executeQuery("SELECT * FROM ProductList WHERE productID = "+productID+" AND trasactionID = "+transactionID);
		
		if(rs.next()){

			Integer integer = rs.getInt("quantity");
			old_quantity = integer.intValue();
			prod_initial_final_price = rs.getInt("finalPrice");

			double per_item_price = prod_initial_final_price/old_quantity;

			updated_quantity = old_quantity - return_quantity;
			prod_new_final_price = per_item_price*updated_quantity;

			price_diff = prod_initial_final_price - prod_new_final_price;
			new_total_price = initial_total_price - price_diff;

			stmt.executeUpdate("UPDATE ProductList SET quantity = "+updated_quantity+" WHERE productID = "+productID+" AND trasactionID = "+trasactionID);
			stmt.executeUpdate("UPDATE ProductList SET finalPrice = "+prod_new_final_price+" WHERE productID = "+productID+" AND trasactionID = "+trasactionID);
			
			stmt.executeUpdate("UPDATE Transactions SET TotalPrice = "+new_total_price+"WHERE storeID = "+storeID+" AND trasactionID = "+trasactionID);

		}else{
			System.out.println("Product List not found.");
		}


		rs = stmt.executeQuery("SELECT * FROM InventoryInfo WHERE productID = "+productID+" AND storeID = "+transactionID);
		
		if(rs.next())
		{
			Integer integer = rs.getInt("quantity");

			old_quantity_inventory = integer.intValue();

			new_quantity_inventory = old_quantity_inventory + return_quantity;

			stmt.executeUpdate("UPDATE InventoryInfo SET quantity = "+new_quantity_inventory+" WHERE productID = "+productID+" AND storeID = "+storeID);

		}	
		else
		{
			System.out.println("Inventory Info not found...adding a new row.");
			String productionDate = '0000-00-00';
			String expirationDate = '0000-00-00';
			stmt.executeUpdate("INSERT into InventoryInfo(productID, storeID, productionDate, expirationDate, quantity) VALUES("+productID+","+storeID+",\'"+productionDate+"\',\'"+expirationDate+"\',"+return_quantity+")");
		}

		System.out.println("Successfully returned the mentioned items.");

	}
	catch(Throwable oops)
	{
		oops.printStackTrace();
	}
}