import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Data implements Serializable {
	
	// ALL INFORMATION STORED ON THESE TWO ARRAYLISTS, SERIALIZED WHEN USER LOGS OUT
	
	static ArrayList <Course> courseList = new ArrayList <Course> ();
	
	static ArrayList <Student> studentList = new ArrayList <Student> ();

	// CREATING COURSE OBJECT AND ADDS TO INSTRUCTOR FROM CSV FILE
	
	public Data (String [] myUniversityCourses) {
		
		Course course = new Course (myUniversityCourses);
		
		courseList.add(course);
	}
	
	// ADDS STUDENT TO STUDENTLIST

	public Data (Student student) {
		
		studentList.add(student);
	}
	
	public static void readCSV () {
		
		// READS COURSE INFORMATION FROM CSV
		
		String myUniversityCourses = "MyUniversityCourses.csv";
		
		try {
			
			FileReader fileReader = new FileReader (myUniversityCourses);
			
			BufferedReader bufferedReader = new BufferedReader (fileReader);
			
			bufferedReader.readLine();
			
			String line = null;
			
			while ((line= bufferedReader.readLine()) != null) {
				
				String [] myUniversityCoursesArray = line.split(",");
	
				Data courseList = new Data (myUniversityCoursesArray);
				
			}
			
			bufferedReader.close();
		}
		
		catch (Exception exception) {
			
			exception.printStackTrace();
		}
	}
}
