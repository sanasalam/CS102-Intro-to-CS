import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.Serializable;

public class User implements Serializable {
	
	static Scanner scanner = new Scanner (System.in);
		
	protected String username;
	protected String password;
	protected String firstName;
	protected String lastName;
	
	// USER CONSTRUCTOR
	
	public User (String username, String password, String firstName, String lastName) {
		
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName (User user) {
		return (user.firstName);
	}
	
	public String getLastName (User user) {
		return (user.lastName);
	}
}
