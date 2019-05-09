package nurse.pals;

import java.util.Comparator;


public class User {
	
	
	
	private String username;
	private String password;
	
	public User() {
		super();
	}
	
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	  @Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}


	public static Comparator<User> CompareByPass = new Comparator<User>() {
	  
	  @Override public int compare(User p1, User p2) { return
	  p1.password.compareTo(p2.password); } };
	  
	  public static Comparator<User> CompareByUsername = new Comparator<User>() {
		  
		  @Override public int compare(User p1, User p2) { return
		  p1.username.compareTo(p2.username); } };
		  
		  
		  @Override
		    public boolean equals(Object obj) {
		        return (this.username.equals(((User) obj).username)
		                && this.password.equals(((User) obj).password));
		    }
	 
	
	

}
