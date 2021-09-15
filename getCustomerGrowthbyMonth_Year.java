
public static void getCustomerGrowthbyMonth()
{

	System.out.println("Enter the year, whose monthly customer growth report you want (in format YYYY): ");
	String year = sc.next();
	System.out.println();

	System.out.println("Enter the month (in format MM): ");
	String month = sc.next();
	System.out.println();

	System.out.println("Getting total customer growth (across all stores) for the entered month...");
	System.out.println();

	try
	{
		stmt = conn.createStatement();
		//SELECT COUNT(customerID) FROM SignsUp WHERE signUpDate BETWEEN '2009-3-1' AND '2012-12-25';

		ResultSet rs = stmt.executeQuery("SELECT COUNT(customerID) AS total_enrolls FROM SignsUp WHERE MONTH(signUpDate) = \'"+month+"\' AND YEAR(signUpDate) = \'"+year+"\'");
		
		while(rs.next())
		{
			Integer integer = rs.getInt("total_enrolls");
			
			int total_enrolls = integer != null ? integer.intValue() : 0;

		}

		System.out.println("The total customer growth (across all stores) for the entered month is: "+total_enrolls+" customers.");

	}
	catch(Throwable oops)
	{
		oops.printStackTrace();
	}
}


public static void getCustomerGrowthbyYear()
{

	System.out.println("Enter the year, whose customer growth report you want (in format YYYY): ");
	String year = sc.next();
	System.out.println();

	System.out.println("Getting total customer growth (across all stores) for the entered year...");
	System.out.println();

	try
	{
		stmt = conn.createStatement();
		//SELECT COUNT(customerID) FROM SignsUp WHERE signUpDate BETWEEN '2009-3-1' AND '2012-12-25';

		ResultSet rs = stmt.executeQuery("SELECT COUNT(customerID) AS total_enrolls FROM SignsUp WHERE YEAR(signUpDate) = \'"+year+"\'");
		
		while(rs.next())
		{
			Integer integer = rs.getInt("total_enrolls");
			
			int total_enrolls = integer != null ? integer.intValue() : 0;

		}

		System.out.println("The total customer growth (across all stores) for the entered year is: "+total_enrolls+" customers.");

	}
	catch(Throwable oops)
	{
		oops.printStackTrace();
	}
}