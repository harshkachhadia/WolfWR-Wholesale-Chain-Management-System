public static void getSalesbyYear(String year){
	System.out.println("Getting total sales for the entered year...");
	System.out.println();

	try{
		stmt = conn.createStatement();

		// percentSymbol = '%';
		// date = year+percentSymbol;

		ResultSet rs = stmt.executeQuery("SELECT SUM(TotalPrice) AS Total_Sales FROM Transactions WHERE YEAR(purchaseDate) = "+year+" ");
		
		
		while(rs.next())
		{
			Integer integer = rs.getInt("Total_Sales");
			
			int total_sales = integer != null ? integer.intValue() : 0;

		}

		System.out.println("The total sales for the entered year is: "+total_sales+"$");

	}
	catch(Throwable oops){
		oops.printStackTrace();
	}
}