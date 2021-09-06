package model;

public class UserAccount {
	private String username;
	private String password;
	private String gender;
	private String career;
	private String birthday;
	private String fBrowser;

	public UserAccount(String un, String p, String g, String c, String b, String br) {
		username = un;
		password = p;
		gender = g;
		career = c;
		birthday = b;
		fBrowser = br;
	}

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	public String getGender() {
		return gender;
	}

	public String getCareer() {
		return career;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getfBrowser() {
		return fBrowser;
	}
	
}
