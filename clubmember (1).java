public static void addClubMember(){
	System.out.println("Enter ClubMemberInformation");
	System.out.print("ClubMember firstName:");
	String firstName= sc.next();
	System.out.println();
	System.out.print("ClubMember lastName: ");
	String lastName= sc.next();
	System.out.println();
	System.out.print("ClubMember PhoneNumber: ");
	String phoneNumber = sc.next();
	System.out.println();
	System.out.print("ClubMember Address:");
	String address= sc.next();
	System.out.println();
	System.out.print("ClubMember Email");
	String email= sc.next();
	System.out.println();
	System.out.print("ClubMember membershipstatus(Gold/Platinum):");
	String membershipLevel=sc.next();
	System.out.println();
	try{
		stmt = conn.createStatement();                                     
		stmt.executeUpdate("INSERT INTO ClubMember(firstName, lastName,PhoneNumber, Address, Email,membershipLevel) VALUES (\'" + firstName+ "\',\'" + lastName+ "\',\'" + PhoneNumber+ "\',\'" + Address+ "\',\'"+ Email + "\',\'"+membershipLevel + "\')");
		stmt.close();
		conn.close();
	}
	catch(Throwable oops){
		oops.printStackTrace();
	}
}
public static void deleteClubMember(){
	System.out.print("Enter ClubMemberID to be deleted");
	String clubMemberID=sc.next();
	System.out.println();
	try{
		stmt=conn.createStatement();
//we setActive status to false instead of deleting clubmember
		stmt.executeUpdate("UPDATE ClubMember SET ActiveStatus ='false' WHERE customerID= " + clubMemberID);
	
	}
	catch(Throwable oops){
		oops.printStackTrace();
	}
}

public static void updateClubMember(){
	System.out.println("enter ClubMemberID");
	String clubMemberID=sc.next();
	System.out.println("choose field to be update");
	System.out.println("1: firstName");
	System.out.println("2: lastName");
	System.out.println("3: phoneNumber");
	System.out.println("4: Address");
	System.out.println("5: Email");
	System.out.println("6: MembershipLevel");
	int option= sc.nextInt();
	switch(option){
		case 1:
		updateClubMemberFirstName(clubMemberID);
		break;
		case 2:
		updateClubMemberLastName(clubMemberID);
		break;
		case 3:
		updateClubMemberPhoneNumber(clubMemberID);
		break;
		case 4:
		updateClubMemberAddress(clubMemberID);
		break;
		case 5:
		updateClubMemberEmail(clubMemberID);
		break;
		case 6:
		updateClubMemberMembershipLevel(clubMemberID);
		break;
	}
} 
public static void updateClubMemberFirstName(String clubMemberID){
	System.out.println("enter firstname of clubMember");
	String firstName=sc.next();
	try{
		stmt=conn.createStatement();
		stmt.executeUpdate("UPDATE ClubMember SET firstName =\'" + firstName + "\' WHERE customerID= " + clubMemberID);
		
	}
	catch(Throwable oops){
		oops.printStackTrace();
	}
}
public static void updateClubMemberLastName(String clubMemberID){
	System.out.println("enter lastName of clubMember");
	String lastName=sc.next();
	try{
		stmt=conn.createStatement();
		stmt.executeUpdate("UPDATE ClubMember SET lastName =\'" + lastName + "\' WHERE customerID= " + clubMemberID);
	}
	catch(Throwable oops){
		oops.printStackTrace();
	}
}
public static void updateClubMemberPhoneNumber(String clubMemberID){
	System.out.println("enter PhoneNumber of clubMember");
	String phoneNumber=sc.next();
	try{
		stmt=conn.createStatement();
		stmt.executeUpdate("UPDATE ClubMember SET PhoneNumber =\'" + phoneNumber + "\' WHERE customerID= " + clubMemberID);
	}
	catch(Throwable oops){
		oops.printStackTrace();
	}
}
public static void updateClubMemberAddress(String clubMemberID){
	System.out.println("enter address of clubMember");
	String address=sc.next();
	try{
		stmt=conn.createStatement();
		stmt.executeUpdate("UPDATE ClubMember SET Address =\'" + address + "\' WHERE customerID= " + clubMemberID);
	}
	catch(Throwable oops){
            oops.printStackTrace();
	}
}
public static void updateClubMemberEmail(String clubMemberID){
	System.out.println("enter email of clubMember");
	String email=sc.next();
	try{
		stmt=conn.createStatement();
		stmt.executeUpdate("UPDATE ClubMember SET Email =\'"+ email +"\' WHERE customerID= " + clubMemberID);
	}


	catch(Throwable oops){
		oops.printStackTrace();
	}
}
public static void updateClubMemberMembershipLevel(String clubMemberID){
	System.out.println("enter membershiplevel of clubMember");
	String membershipLevel=sc.next();
	try{
		stmt=conn.createStatement();
		stmt.executeUpdate("UPDATE ClubMember SET membershipLevel =\'" + membershipLevel + "\' WHERE customerID= " + clubMemberID);
	}
	catch(Throwable oops){
		oops.printStackTrace();
	}
		
}
