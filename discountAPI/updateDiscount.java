public static void updateDiscount(){
    

    Boolean flag = true;

    while(flag){

        System.out.println("Choose field to Update Discount");
        System.out.println("1: Change Discount Percentage");
        System.out.println("2: Change Start Date for a discount");
        System.out.println("3: Change End Date for a discount");

        System.out.println("Enter your choice: ");
        int option = sc.nextInt();


        switch(option){
            case 1:
                try{
                    System.out.println("Enter the storeID : ");
                    int storeID = sc.nextInt();                        
                        
                    System.out.println("Enter the productID : ");
                    int productID = sc.nextInt();                      
                        
                    System.out.println("Enter the new Discount Percentage : ");
                    int disc_percentage = sc.nextInt();

                    stmt = conn.createStatement();
                    stmt.executeUpdate("UPDATE Discount SET discountPercentage = " + disc_percentage + " WHERE storeID = " +storeID+" AND productID = "+productID+" ");
                                        
                }catch(Throwable oops){
                    oops.printStackTrace();
                }
                
                break;
		
            case 2:    
                try
                {
                    System.out.println("Enter the storeID : ");
                    int storeID = sc.nextInt();                        
                        
                    System.out.println("Enter the productID : ");
                    int productID = sc.nextInt(); 
                        
                    System.out.println("Enter the Start Date : ");
                    String start_date = sc.next();
                        
                    stmt = conn.createStatement();
                    stmt.executeUpdate("UPDATE Discount SET startDate = " + start_date + " WHERE storeID = " +storeID+" AND productID = "+productID+" ");
                                           
                }
                catch(Throwable oops)
                {
                    oops.printStackTrace();
                }

                break;
		
            case 3:
                try
                {
                    System.out.println("Enter the storeID : ");
                    int storeID = sc.nextInt();              

                    System.out.println("Enter the productID : ");    
                    int productID = sc.nextInt();              

                    System.out.println("Enter the End Date : ");
                    String end_date = sc.next();

                    stmt = conn.createStatement();
                    stmt.executeUpdate("UPDATE Discount SET endDate = " + end_date + " WHERE storeID = " +storeID+ " AND productID = "+productID+" ");
                      
                }
                catch(Throwable oops)
                {
                    oops.printStackTrace();
                }

	
		        break;

            default:
                {
                    System.out.println("Please try again ... enter a valid choice");
                }    

            System.out.println("Do you wish to update anything else ? [y/n] : ");
            char c = sc.next().charAt(0);

            if(c == 'y' || c == 'Y')
            {
                continue;
            }
            else if(c == 'n' || c == 'N')
            {
                flag = false;
                break;
            }
            else
            {
                System.out.println("Wrong input!!");
                updateDiscount();
            }
        }

    }
