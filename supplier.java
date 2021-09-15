public static void addNewSupplier(){
  System.out.println("Enter Supplier Information");

        System.out.print("Supplier Name : ");
        String suppName = sc.next();
        System.out.println();

        System.out.print("Supplier Phone: ");
        String suppPhone = sc.next();
        System.out.println();

	System.out.print("Supplier Email: ");
        String suppEmail = sc.next();
        System.out.println();

	System.out.print("Supplier Location: ");
        String suppLocation = sc.next();
        System.out.println();


        try{
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Supplier (supplierName, phoneNumber, Email, Location) VALUES (\"" + suppName + "\",\"" + suppPhone + "\",\"" + suppEmail + "\,\"" + suppLocation + "\"")");

            stmt.close();
            conn.close();
        }catch(Throwable oops){
            oops.printStackTrace();
        }


}

public static void updateSupplierInfo(){
    
    boolean flag = true;

    while(flag){

        System.out.print("Enter the supplier ID for which you want to update the fields : ");
        int suppID = sc.nextInt();
        System.out.println();

        System.out.println("Choose field to Update Supplier Information");
        System.out.println("1: Change Supplier Name");
        System.out.println("2: Change Supplier Phone");
        System.out.println("3: Change Supplier Email");
        System.out.println("4: Change Supplier Location");

        int option = sc.nextInt();

        switch(option){
            case 1:
                try{

                    System.out.print("Enter the Supplier Name : ");
                    String suppName = sc.next();
                    stmt = conn.createStatement();
                    stmt.executeUpdate("UPDATE Supplier SET supplierName = \'" + suppName + "\' WHERE supplierID = " + suppName);

                }catch(Throwable oops){
                    oops.printStackTrace();
                }
                
                break;
            case 2:
                try{
                    System.out.print("Enter the Supplier Phone : ");
                    String suppPhone = sc.next();
                    stmt = conn.createStatement();
                    stmt.executeUpdate("UPDATE Supplier SET phoneNumber = \'" + suppPhone + "\' WHERE supplierID = " + suppID);

                    }catch(Throwable oops){
                     oops.printStackTrace();
                    }
                break;

            case 3:
                try{

                    System.out.print("Enter the Supplier Email : ");
                    String suppEmail = sc.next();
                    stmt = conn.createStatement();
                    stmt.executeUpdate("UPDATE Supplier SET Email = \'" + suppEmail + "\' WHERE supplierID = " + suppID);

                }catch(Throwable oops){
                    oops.printStackTrace();
                }
                 break;
            case 4:
                try{

                    System.out.print("Enter the Supplier Location : ");
                    String suppLocation = sc.next();
                    stmt = conn.createStatement();
                    stmt.executeUpdate("UPDATE Supplier SET Location = \'" + suppLocation + "\' WHERE supplierID = " + suppID);

                    stmt.close();
                    conn.close();
                }catch(Throwable oops){
                    oops.printStackTrace();
                }
                break;
            default:
                System.out.println("Wrong Input!!!");
                updateSupplierInfo();

        }

        System.out.print("Do you wish to update anything else ? [y/n] : ");
        char c = sc.next().charAt(0);

        if(c == 'y' || c == 'Y'){
            continue;
        }else if(c == 'n' || c == 'N'){
            flag = 0;
        }else{
            System.out.println("Wrong input!! Please run the code again...");
            updateSupplierInfo();
        }

    }

}





public static void deleteSupplier(){
        System.out.println("Delete Supplier Information");

        System.out.print("Supplier ID : ");
        String suppID = sc.next();
        System.out.println();

        try{
            stmt = conn.createStatement();
	//PLEASE CHECK//
           //stmt.executeUpdate("DELETE Supplier (supplierID) VALUES (\"" + suppID + "\"")");//

            stmt.close();
            conn.close();
        }catch(Throwable oops){
            oops.printStackTrace();
        }

    }

