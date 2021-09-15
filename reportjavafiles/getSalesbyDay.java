//getSalesbyDay(date);

public static void getSalesbyDay(String date)
{
	System.out.println("Getting total sales for the entered date...");
	System.out.println();

	try
	{
		stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT SUM(TotalPrice) AS Total_Sales FROM Transactions WHERE PurchaseDate = "+date+"  ");
		
		
		while(rs.next())
		{
			Integer integer = rs.getInt("Total_Sales");
			
			int total_sales = integer != null ? integer.intValue() : 0;

		}



		System.out.println("The total sales for the entered date is: "+total_sales+"$");

	}
	catch(Throwable oops)
	{
		oops.printStackTrace();
	}
}