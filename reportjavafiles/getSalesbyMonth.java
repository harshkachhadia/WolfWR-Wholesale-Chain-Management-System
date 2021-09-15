//getSalesbyMonth(month);

//entered month format will be YYYY-MM
public static void getSalesbyMonth(String month, String year)
{
	System.out.println("Getting total sales for the entered month...");
	System.out.println();

	try
	{
		stmt = conn.createStatement();

		// percentSymbol = '%';
		// date = month+percentSymbol;

		ResultSet rs = stmt.executeQuery("SELECT SUM(TotalPrice) AS Total_Sales FROM Transactions WHERE  MONTH(purchaseDate)="+month+" AND YEAR(purchaseDate) = "+year+" ");
		//"SELECT SUM(TotalPrice) AS Total_Sales FROM Transactions WHERE PurchaseDate LIKE "+date+" " 
		
		while(rs.next()){
			
			Integer integer = rs.getInt("Total_Sales");
			
			int total_sales = integer != null ? integer.intValue() : 0;

		}

		System.out.println("The total sales for the entered month is: "+total_sales+"$");

	}
	catch(Throwable oops)
	{
		oops.printStackTrace();
	}
}