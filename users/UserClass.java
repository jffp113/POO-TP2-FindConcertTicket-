package users;

public class UserClass implements User {
	//Constants
	
	//Variables 
	private String userName;
	private String password;
	
	
	//Constructor
	/**
	 * This is the constructor for the userclass
	 * @param userName User name
	 * @param password User password
	 */
	public UserClass(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean equals(Object obj) {
		if (this == obj) return true; 
		
		if (obj == null) return false;
		
		if (!(obj instanceof User)) return false;
			
		User other = (User) obj; 
		
		if (userName == null) {
			if (other.getUserName() != null) return false;
			else return true;
		}
		else return userName.equals(other.getUserName());
	}
}
