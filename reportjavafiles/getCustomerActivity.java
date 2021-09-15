
public static void getCustomerActivity(int customerID, int storeID, String startDate, String endDate)
{
	System.out.println("Getting total sales for the entered store in the required time period...");
	System.out.println();

	try
	{
		stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT SUM(TotalPrice) AS Total_Activity FROM Transactions WHERE customerID = "+customerID+" AND PurchaseDate BETWEEN "+startDate+" AND "+endDate+" ");

		while(rs.next())
		{
			Integer integer = rs.getInt("Total_Activity");
			
			int total_sales = integer != null ? integer.intValue() : 0;

		}

		System.out.println("The total stock for the entered customerID: "+customerID+" and storeID: "+storeID+" between dates "+startDate+" and "+endDate+" is: "+total_activity+"$");

	}
	catch(Throwable oops)
	{
		oops.printStackTrace();
	}
}