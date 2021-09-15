public static void generateRewardChecks(){
    
    System.out.println("Generate Reward Checks");

    System.out.println();
    int rewardPercentage = 5;
    System.out.println("Reward Percentage is set to : "+rewardPercentage+"% (default for all Platinum Customers)");
    System.out.println("Generating Reward Checks...");

    ArrayList<Integer> platinum_customerIDs = new ArrayList<>();
    HashMap <Integer,Integer> hp = new HashMap <>();

    try{   

        stmt = conn.createStatement();
        
        ResultSet ct = stmt.executeQuery("SELECT customerID FROM ClubMember WHERE membershipLevel = 'Platinum';");
        
        while(ct.next()){
            platinum_customerIDs.add(ct.getInt("customerID"));
        }

        for (Integer i : platinum_customerIDs)
            
            ct = stmt.executeQuery("Select customerID, SUM(TotalPrice * " + rewardPercentage +") AS REWARDS  FROM Transactions where (customerID="+i+" AND purchaseDate BETWEEN '2005-1-2' AND '2015-10-05');");
            
            while(ct.next()){
                hp.put(ct.getInt("customerID"),ct.getInt("REWARDS"));
            }

        System.out.println("customerID -> Reward Amount");
        System.out.println("----------------------------------");
        
        for(Map.Entry m : hp.entrySet()){
            System.out.println(m.getKey() + " -> " + m.getValue());
        }

    }catch(Throwable oops){
        oops.printStackTrace();
    }

}
