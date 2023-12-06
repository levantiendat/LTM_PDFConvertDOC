package model.bean;

public class Account {
	private String Username;
	private String Password;
	private String Email;
	public Account() {
		super();
	}
	public Account(String username, String password, String email) {
		super();
		Username = username;
		Password = password;
		Email = email;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
}
