

public static void maintainingInventoryRecords(){
	System.out.println("Menu");
	System.out.println("1: Add product to Inventory and store");
	System.out.println("2: Update ProductInformation in Inventory");
	System.out.println("3: Update Product Information in Store");
	System.out.println("4: Delete Product from Store");
	System.out.println("5: Update store with returns");
	System.out.println("6: Transfer product between two stores");

	int option=sc.nextInt();
	switch(option){
		case 1:
		addNewInventory();
		break;
		case 2:
		updateProductInInventory();
		break;
		case 3:
		updateProductInStore();
		break;
		case 4:
		deleteProductInStore();
		break;
		case 5:
		updateStoreWithReturns();
		break;
		case 6:
		//we have to check quantity constraint
		transferProductBetweenStores();
		break;
	}

}


public static void addNewInventory()
{
	System.out.print("Enter the storeID: ");
	int storeID = sc.nextInt();
	System.out.println();

	System.out.print("Enter the productName: ");
	String productName = sc.next();
	System.out.println();

	System.out.print("Enter the productionDate (in YYYY-MM-DD format): ");
	String productionDate = sc.next();
	System.out.println();

	System.out.print("Enter the expirationDate (in YYYY-MM-DD format): ");
	String expirationDate = sc.next();
	System.out.println();

	System.out.print("Enter the newly arrived items quantity: ");
	int quantity = sc.nextInt();
	System.out.println();

	System.out.print("Enter the buyPrice: ");
	int buyPrice = sc.nextInt();
	System.out.println();

	System.out.print("Enter the marketPrice: ");
	int marketPrice = sc.nextInt();
	System.out.println();

	System.out.print("Adding new arrivals to the inventory...");
	System.out.println();

	int productID;

	try
	{
		stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT productID FROM productTable WHERE productName = \'" + productName + "\' AND buyPrice = " + buyPrice + " AND marketPrice = " + marketPrice);
		
		while(rs.next())
		{
			Integer integer = rs.getInt("productID");

			if(integer != null)
			{
				productID = integer.intValue();

			}	
			else
			{
				stmt.executeUpdate("INSERT into productTable(productName, buyPrice, marketPrice) VALUES (\'"+productName+"\',"+ buyPrice+","+ marketPrice+")");
				rs = stmt.executeQuery("SELECT productID FROM productTable WHERE productName = \'"+productName+"\' AND buyPrice = "+buyPrice+" AND marketPrice = "+marketPrice);
				while(rs.next())
				{
					integer = rs.getInt("productID");
					productID = integer.intValue();
				}
			}
		}

		rs = stmt.executeQuery("SELECT * FROM InventoryInfo WHERE productID = \'"+productID+"\' AND storeID = "+storeID);
		
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
				stmt.executeUpdate("INSERT into InventoryInfo(productID, storeID, productionDate, expirationDate, quantity) VALUES(" + productID + "," + storeID + ",\'" + productionDate + "\',\'" + expirationDate + "\', " + quantity + ")");
			}
		}

		System.out.println("Successfully added newly arrived inventory....");

	}
	catch(Throwable oops)
	{
		oops.printStackTrace();
	}
}



public static void updateProductInInventory(){
	System.out.print("enter productID to be updated");
	Int productID=sc.nextInt();
	System.out.println();

	System.out.println("Choose the field to be updated");
	System.out.println("1: Product Name");
	System.out.println("2: Buy price");
	System.out.println("3: market price");

	Int option= sc.nextInt();

	switch(option){
		case 1:
			System.out.println("enter new product name:");
			String productName=sc.next();
			try{
				stmt=conn.createStatement();
				stmt.executeUpdate("UPDATE productTable SET productName=\'"+ productName + "\'WHERE productID =" + productID);
			}
			catch(Throwable oops){
				oops.printStackTrace();
			}
			break;
		case 2:
			System.out.println("enter Buy price to update:");
			String buyPrice=sc.next();
			try{
				stmt=conn.createStatement();
				stmt.executeUpdate("UPDATE productTable SET buyPrice=\'"+ buyPrice + "\'WHERE productID =" + productID);
			}
			catch(Throwable oops){
				oops.printStackTrace();
			}
			break;
		case 3:
			System.out.println("enter marketprice to update:");
			String marketPrice=sc.next();
			try{
				stmt=conn.createStatement();
				stmt.executeUpdate("UPDATE productTable SET marketPrice=\'"+ marketPrice + "\'WHERE productID =" + productID);
			}
			catch(Throwable oops){
				oops.printStackTrace();
			}
			break;
	}
}



public static void updateProductInStore(){
	System.out.print("enter productID to be to be updated");
	String productID=scnext();
	System.out.println();

	System.out.println("enter storeID to be updated");
	String storeID= sc.next();

	System.out.println("1: productionDate");
	System.out.println("2: expiration Date");
	System.out.println("3: quantity");

	int option= sc.nextInt();

	switch(option){
		case 1:
			System.out.println("enter  production date in YYYY-MM-DD format to update:");
			String productionDate=sc.next();
			try{
				stmt=conn.createStatement();
				stmt.executeUpdate("UPDATE productTable SET productionDate = \'"+ productionDate + "\' WHERE productID =" + productID +" AND storeID = " +storeID);
			}
			catch(Throwable oops){
				oops.printStackTrace();
			}
		break;
		case 2:
			System.out.println("enter expirationdate in YYYY-MM-DD to update :");
			String expirationDate=sc.next();
			try{
				stmt=conn.createStatement();
				stmt.executeUpdate("UPDATE productTable SET expirationDate= \'"+ expirationDate + "\' WHERE productID =" + productID+" AND storeID = " +storeID);
			}
			catch(Throwable oops){
				oops.printStackTrace();
			}
		break;
		case 3:
			System.out.println("enter quantity  to update :");
			String quantity=sc.next();
			try{
			
				stmt=conn.createStatement();
				stmt.executeUpdate("UPDATE productTable SET quantity = "+ quantity + " WHERE productID =" + productID +" AND storeID = " + storeID);
			
			}catch(Throwable oops){
				oops.printStackTrace();
			}
	}
}


public static void deleteProductInStore(){

	System.out.println("enter productID to be deleted");
	String productID = sc.next();
	System.out.println();

	System.out.print("enter storeID in which product to be deleted");
	String storeID = sc.next();
	System.out.println();

	try{
		stmt=conn.createStatement();
		stmt.executeUpdate("DELETE FROM InventoryInfo WHERE productID = " + productID + " AND storeID = " + storeID);
	}
	catch(Throwable oops){
		oops.printStackTrace();
	}
	
}

public statc void transferProductBetweenStores(){
	System.out.print("Enter productID to be transferred : ");
	String productID=sc.next();
	System.out.println();

	System.out.println("enter storeID of product source : ");
	String senderStoreID=sc.next();
	System.out.println();

	System.out.println("enter storeID of product destination : ");
	String receiverStoreID=sc.next();
	System.out.println();

	System.out.println("enter quantity to be transferred : ");
	int quantity = sc.nextInt();
//start transaction
	try{
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT quantity FROM InventoryInfo WHERE productID = " + productID+" AND storeID = "+senderStoreID);
		int availableInSender = rs.getInt("quantity");
		
		rs = stmt.executeQuery("SELECT quantity FROM InventoryInfo WHERE productID = " + productID+" AND storeID="+receiverStoreID);
		int availableInReceiver=rs.getInt("quantity");
		
		if(availableInSender >= quantity){
			
			try{
				int newQuantityInSender=availableInSender-quantity;
				int newQuantityInReceiver=availableInReceiver+quantity;
				stmt=conn.createStatement();
				stmt.executeUpdate("UPDATE InventoryInfo SET quantity= "+ newQuantityInSender + " WHERE storeID = " + senderStoreID);
				stmt.executeUpdate("UPDATE InventoryInfo SET quantity= "+ newQuantityInReceiver + " WHERE storeID = " + receiverStoreID);
			}
			catch(Throwable oops){
				oops.printStackTrace();
			}
			
		}
		else{
			System.out.println("Insufficient quantity in source");
		}
	}
	catch(Throwable oops){
				oops.printStackTrace();
	}
}