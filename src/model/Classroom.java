package model;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
private static List<UserAccount> accounts;
	
	public Classroom() {
		accounts = new ArrayList<>();
	}
	
	public static void addUser(String un, String p, String g, String c, String b, String br) {
		accounts.add(new UserAccount(un, p, g, c, b, br));
	}
	
	public List<UserAccount> getAccounts(){
		return accounts;
	}
}
