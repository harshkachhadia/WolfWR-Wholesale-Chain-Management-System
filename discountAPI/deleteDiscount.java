public static void deleteDiscount(){
	System.out.println("Delete Discount Process");
    System.out.println("------------------------------");

    System.out.print("Enter the Product ID of the discount to be deleted : ");
    int productID = sc.nextInt();
    System.out.println();

    System.out.print("Enter the Store ID of the discount to be deleted: ");
    int storeID= sc.nextInt();
    System.out.println();

	try{
        stmt = conn.createStatement();
        stmt.executeUpdate("DELETE from Discount WHERE storeID = "+storeID+" AND productID = "+productID+" ");
    }catch(Throwable oops){
        oops.printStackTrace();
    }

}
