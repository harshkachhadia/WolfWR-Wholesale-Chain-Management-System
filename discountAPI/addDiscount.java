public static void addDiscount(){

    System.out.println("Enter Discount Information");
    System.out.println("------------------------------");

    System.out.print("Enter the Product ID : ");
    int productID = sc.nextInt();
    System.out.println();

    System.out.print("Enter the Store ID: ");
    int storeID= sc.nextInt();
    System.out.println();

	System.out.print("Enter the Discount Percentage: ");
    int discountPercentage = sc.nextInt();
    System.out.println();

	System.out.print("Enter the Start Date (in YYYY-MM-DD format): ");
    String startDate = sc.next();
    System.out.println();
	
	System.out.print("Enter the End Date (in YYYY-MM-DD format): ");
    String endDate = sc.next();
    System.out.println();

    try{
        stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO Discount (productID, storeID, DiscountPercentage, startDate, endDate) VALUES (" + productID + ", " + storeID + ", " + discountPercentage + " , \'" + startDate + "\',  \'" + endDate + "\')");

    }
    catch(Throwable oops){
        oops.printStackTrace();
    }
        

}