import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

public class CourseRegistrationSystem implements Serializable {

	static Scanner scanner = new Scanner (System.in);

	// CREATE ONE ADMIN
	
	public static Admin adminObject = new Admin ("admin1", "admin001", "Anasse", "Bari");
	
	public static void main (String [] args) { 
		
		System.out.println("Welcome to Course Regisration System");
 		
		serialize();	
	}
	
	public static void serialize () {
		
		// READS FROM CSV FILE COURSE INFORMATION IF FIRST TIME LOGGING IN, ELSE READS FROM SER FILE
		// READS STUDENT INFORMATION FROM SER FILE
	
		try{
			
			//FileInputSystem receives bytes from a file
			FileInputStream fis = new FileInputStream("courseData.ser");
			      
			 //ObjectInputStream does the deserialization-- it reconstructs the data into an object
			 ObjectInputStream ois = new ObjectInputStream(fis);
			      
			 //Cast as Employee. readObject will take the object from ObjectInputStream
			 Data.courseList = (ArrayList<Course>)ois.readObject();
			 ois.close();
			 fis.close();
		}
		catch (FileNotFoundException e) {
			Data.readCSV();
		}
		catch (IOException ioe) {
			 ioe.printStackTrace();
			 return;
		}
		catch (ClassNotFoundException cnfe) {
			  cnfe.printStackTrace();
			  return;
		}
			
		try{
			 //FileInputSystem recieves bytes from a file
		     FileInputStream fis2 = new FileInputStream("studentData.ser");
		      
		     //ObjectInputStream does the deserialization-- it reconstructs the data into an object
		     ObjectInputStream ois2 = new ObjectInputStream(fis2);
		      
		      //Cast as Employee. readObject will take the object from ObjectInput
		     Data.studentList = (ArrayList<Student>)ois2.readObject();
		      ois2.close();
		      fis2.close();
		    }
		catch(IOException ioe) {
		      ioe.printStackTrace();
		      return;
		}
		catch (ClassNotFoundException cnfe) {
			  cnfe.printStackTrace();
			  return;
		}
		logIn();
	}
	
	public static void logIn () {
		
		// PROMPTS TO USER TO SAY WHETHER THEY ARE ADMIN OR STUDENT, THEN LOGS IN TO THEIR ACCOUNT WITH USERNAME AND PASSWORD AND CALLS APPROPRIATE MENU
			
			System.out.println("Please enter 'student' for student account or 'admin' for admin account.");
			
			String logInInput = scanner.next();
			
			if (logInInput.equals("student")) {
				System.out.println ("Please enter your student username:");
				String logInUsername = scanner.next();
				System.out.println("Please enter your password");
				String logInPassword = scanner.next();
				for (int i = 0; i < Data.studentList.size(); i++) {
					if (logInUsername.equals(Data.studentList.get(i).getUsername())) {
						if (logInPassword.equals(Data.studentList.get(i).getPassword())) {
							System.out.println("Welcome, " + Data.studentList.get(i).getFirstName());
							Data.studentList.get(i).menu();
						}
						else if ((i == Data.studentList.size() - 1) && (!(logInPassword.equals(Data.studentList.get(i).getPassword()))))
							System.out.println("Incorrect password entered.");
					}
					else if ((i == Data.studentList.size() - 1) && (!(logInUsername.equals(Data.studentList.get(i).getUsername()))))
						System.out.println("Username does not exist.");
						System.out.println("To add a new student to the System, please log in as an Admin.");
				}
			}
			else if (logInInput.equals("admin")) {
				System.out.println ("Please enter your admin username:");
				String adminLogInUsername = scanner.next();
				System.out.println("Please enter your password");
				String adminLogInPassword = scanner.next();
		
				if (adminLogInUsername.equals(adminObject.username)) {
					if (adminLogInPassword.equals(adminObject.password)) {
						adminObject.menu();
					}
				}
			}
			else {
				System.out.println("Your answer was not recognized.");
			}
			scanner.close();
	}
	
	public static void logOut () {
		
		// LOGS USER OUT OF THE SYSTEM AND SERIALIZED COURSELIST AND STUDENTLIST
		
		System.out.println ("Exiting Course Regisration System");
		try {
			//FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream("courseData.ser");
			
			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			//Writes the specific object to the OOS
			oos.writeObject(Data.courseList);
			
			//Close both streams
			oos.close();
			fos.close();
			System.out.println("Course serialization complete");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try {
			//FileOutput Stream writes data to a file
			FileOutputStream fos2 = new FileOutputStream("studentData.ser");
			
			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
			
			//Writes the specific object to the OOS
			oos2.writeObject(Data.studentList);
			
			//Close both streams
			oos2.close();
			fos2.close();
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		System.out.println ("If you wish to log in again:");
		logIn();
	}
}
