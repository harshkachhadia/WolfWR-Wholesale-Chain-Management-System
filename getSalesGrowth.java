public static void getSalesGrowth()

{

	System.out.println("Enter the storeID: ");
	int storeID = sc.nextInt();
	System.out.println();

	System.out.println("Enter the start date (in format YYYY-MM-DD): ");
	String startDate = sc.next();
	System.out.println();

	System.out.println("Enter the end date (in format YYYY-MM-DD): ");
	String endDate = sc.next();
	System.out.println();	


	System.out.println("Getting total sales for the entered store in the given time period...");
	System.out.println();

	int total_sales;

	try
	{
		stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT * FROM Store WHERE storeID = "+storeID);
		
		while(rs.next())
		{
			Integer integer = rs.getInt("storeID");

			if(integer != null)
			{
				storeID = integer.intValue();

				rs = stmt.executeQuery("SELECT SUM(TotalPrice) AS Total_Sales FROM Transactions WHERE storeID = "+storeID+" AND (PurchaseDate BETWEEN \'"+startDate+"\' AND \'"+endDate+"\')");

				while(rs.next())
				{
					integer = rs.getInt("Total_Sales");
					
					total_sales = integer != null ? integer.intValue() : 0;

				}				

				System.out.println("The total sales for the entered store in the given time period is: $"+total_sales);
			}	
			else
			{
				System.out.println("Invalid storeID entered...");
			}
		}
	}
	catch(Throwable oops)
	{
		oops.printStackTrace();
	}
}