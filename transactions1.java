public static void generateTransaction(){
// we use cashierid to fetch storeID
	System.out.println("enter cashierID");
	String cashierID=sc.next();
	
	double TotalPrice=0;
	int transactionID = 0;
	int discountID=0;
	int storeID=0;
	boolean flag=true;
	double finalPrice=0;
	int marketPrice=0;
	String discountApplied='false';
	
	System.out.print("enter customerID : ");
	String customerID= sc.next();
	System.out.println();

	System.out.print("enter date of purchase(YYYY-MM-DD) : ");
	String purchaseDate=sc.next();
	System.out.println();
	
	try{
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT storeID FROM Staff WHERE staffID = " + staffID);
		
		if(rs.next()){
			
			storeID=rs.getInt("storeID");

			try{
				stmt = conn.createStatement();   
				stmt.executeUpdate("INSERT INTO Transactions(storeID, customerID, cashierID, PurchaseDate, TotalPrice) VALUES (" + storeID+ "," + customerID+ "," + cashierID+ ",\'" + PurchaseDate+ "\',"+ TotalPrice + ")");
				rs = stmt.executeQuery("SELECT transactionID FROM Transactions WHERE storeID = "+storeID+" AND customerID = "+customerID+" AND cashierID = "+cashierID+" AND PurchaseDate = \'"+PurchaseDate+"\' AND TotalPrice = "+TotalPrice);
				
				// while(rs.next()){
				// 	transactionID = rs.getInt("transactionID");
				// }

				if(rs.next()){
					transactionID = rs.getInt("transactionID");
				}

				
				while(flag){
					System.out.print("enter productID : ");
					String productID=sc.next();
					System.out.println();

					System.out.print("enter quantity : ");
					int reqquantity=sc.nextInt();
					System.out.println();

					stmt = conn.createStatement();
					ResultSet rs=stmt.executeQuery("SELECT marketPrice FROM productTable WHERE productID="+productID);
					
					if(rs.next()){
						marketPrice= rs.getInt("marketPrice");
					}
							
					ResultSet rs=stmt.executeQuery("SELECT discountID FROM Discount WHERE productID = " + productID + " AND storeID = "+ storeID + " AND purchaseDate BETWEEN \'" + startDate + "\' AND \'" + endDate + "\'");
					
					if(rs.next()){
						discountID=rs.getInt("discountID");
						
						ResultSet res = stmt.executeQuery("SELECT DiscountPercentage FROM Discount WHERE discountID = "+ discountID);
						
						while(res.next()){
							discountPercentage=res.nextInt("DiscountPercentage");
							finalPrice=reqquantity *(marketPrice-((marketPrice * (double)discountPercentage)/100));
							TotalPrice=TotalPrice+finalPrice;
							discountApplied='true';							
						}

					}else{
						discountApplied='false';
						finalPrice=reqquantity *marketPrice;
						TotalPrice=TotalPrice+finalPrice;
										
					}

					ResultSet res = stmt.executeQuery("SELECT quantity FROM InventoryInfo WHERE productID = "+ discountID + " AND storeID = " + storeID);

					int currQuantity = 0;

					if(res.next()){
						currQuantity = res.getInt("quantity");
						if(currQuantity >= reqquantity){
							currQuantity -= reqquantity;
							stmt.executeUpdate("UPDATE InventoryInfo SET quantity = " + currQuantity + " WHERE productID= " + productID +" AND storeID ="+storeID);
							stmt.executeUpdate("INSERT INTO ProductList(transactionID, productID, quantity, discountApplied, finalPrice) VALUES (" + transactionID+ "," + productID+ "," + reqquantity + "," + discountApplied+ ","+ finalPrice + ")");
						}else{
							System.out.println("Not enough quantity!!!");	
						}
					}else{
						System.out.println("Product not in store!!!");
					}

					// stmt.executeUpdate("UPDATE InventoryInfo SET quantity = quantity - " + reqquantity + " WHERE productID= " + productID +" AND storeID ="+storeID);
					// stmt.executeUpdate("INSERT INTO ProductList(transactionID, productID, quantity, discountApplied, finalPrice) VALUES (\'" + transactionID+ "\',\'" + productID+ "\',\'" + quantity+ "\',\'" + discountApplied+ "\',\'"+ finalPrice + "\')");						 
					
					System.out.print("do you want to add more products (y/n) : ");
					String option=sc.next().charAt(0);
					System.out.println();

					if(option=='y' || option=='Y'){
						continue;
					}else{
						flag=false;
					}
				}

				try{                    
						stmt = conn.createStatement();
						stmt.executeUpdate("UPDATE Transactions SET TotalPrice = " + TotalPrice + " WHERE transactionID = " + transactionID );			
				}
				catch(Throwable oops){
					oops.printStackTrace();
				}
				
			}catch(Throwable oops){
				oops.printStackTrace();
			}		
				
		}else{
			System.out.println("invalid cashier");		
		}
					
	}	
	catch(Throwable oops){
		oops.printStackTrace();
    }
}