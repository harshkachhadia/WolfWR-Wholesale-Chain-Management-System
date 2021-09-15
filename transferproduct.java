public statc void transferProductBetweenStores(){
	int availableInSender=0;
	int availableInReceiver=0;
	String productionDate;
	String expirationDate;

	System.out.print("Enter productID to be transferred:");
	String productID=sc.next();
	System.out.println();

	System.out.print("enter  storeID of product source");
	String senderStoreID=sc.next();
	System.out.println();
	
	System.out.print("enter  storeID of product destination");
	String receiverStoreID=sc.next();
	System.out.println();
	
	System.out.print(" enter quantity to be transferred");
	int quantity=sc.nextInt();
	System.out.println();
	

	try{
		stmt = conn.createStatement();
		rs=stmt.executeQuery("SELECT productionDate, expirationDate, quantity, FROM InventoryInfo WHERE productID = " + productID+" AND storeID="+senderStoreID);
		
		while(rs.next()){
			Date proddate=rs.getDate("productionDate");
			productionDate= proddate.toString();

			Date expdate=rs.getDate("expirationDate");
			expirationDate=expdate.toString();

			availableInSender=rs.getInt("quantity");
		}

		int newQuantityInSender = availableInSender-quantity;

		if(availableInSender >= quantity){
			rs=stmt.executeQuery("SELECT quantity FROM InventoryInfo WHERE productID = " + productID + " AND storeID = " + receiverStoreID);
			
			if(rs.next()){
				availableInReceiver=rs.getInt("quantity");
				try{
					int newQuantityInReceiver=availableInReceiver+quantity;
					stmt=conn.createStatement();
					stmt.executeUpdate("UPDATE InventoryInfo SET quantity= "+ newQuantityInSender + " WHERE storeID =" + senderStoreID);
					stmt.executeUpdate("UPDATE InventoryInfo SET quantity= "+ newQuantityInReceiver + " WHERE storeID =" + receiverStoreID);
				}
				catch(Throwable oops){
					oops.printStackTrace();
				}
			}else{
				try{
					stmt = conn.createStatement();  
					stmt.executeUpdate("UPDATE InventoryInfo SET quantity = "+ newQuantityInSender + " WHERE storeID =" + senderStoreID);					
					stmt.executeUpdate("INSERT INTO InventoryInfo(productID, storeID, productionDate, expirationDate, quantity) VALUES (" + productID+ "," + receiverStoreID + ",\'" + productionDate+ "\',\'"+ expirationDate + "\',"+quantity + ")");
				}
				catch(Throwable oops){
					oops.printStackTrace();
				}
			}

		}else{
			System.out.println("Insufficient quantity in source store");
		}			
		
	}catch(Throwable oops){
		oops.printStackTrace();
	}
}