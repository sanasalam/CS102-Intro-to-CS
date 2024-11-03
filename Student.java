import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User implements StudentInterface, Serializable {
	
	static Scanner scanner = new Scanner (System.in);

	public Student (String username, String password, String firstName, String lastName) {
		
		super (username, password, firstName, lastName);
	}
	
	ArrayList <Course> studentCourseList = new ArrayList <Course>(); 
	
	public void menu () {
		
		// STUDENT MENU WITH STUDENT FUNCTIONS OPTIONS. CALLS METHOD BASED ON STUDENT INPUT CHOICE
		
		System.out.println ("COURSE MANAGEMENT OPITIONS:");
		System.out.println ("V    View Courses");
		System.out.println ("O    View Open Courses");
		System.out.println ("R    Register in Course");
		System.out.println ("W    Withdraw from Course");
		System.out.println ("M    View my Courses");
		System.out.println ("L    Log Out");
		
		System.out.println ("Please enter the letter that corresponds with what you want to do:");
		String firstInput = scanner.next();
		
		if (firstInput.equals("V"))
			this.viewCourses();
		else if (firstInput.equals("O"))
			this.viewOpenCourses();
		else if (firstInput.equals("R"))
			this.registerInCourse();
		else if (firstInput.equals("W"))
			this.withdrawFromCourse();
		else if (firstInput.equals("M"))
			this.viewMyCourses();
		else if (firstInput.equals("L"))
			CourseRegistrationSystem.logOut();
	}
	
	public void viewCourses() {
		
		// PRINTS INFORMATION ON ALL COURSES
		
		System.out.println("Below is information on each course in the system.");
		
		for (int i = 0; i <Data.courseList.size(); i++) {
			
			System.out.println(Data.courseList.get(i).getCourseName());
			
			System.out.println ("Registered Students: ");
			// PRINT STUDENT NAMES
			String viewStudentNames = "";
			for (int j = 0; j < Data.courseList.get(i).getCourseStudentNames().size(); j++) {
				if (j == (Data.courseList.get(i).getCourseStudentNames().size() - 1)) {
					viewStudentNames = viewStudentNames + Data.courseList.get(i).getCourseStudentNames().get(j);
				}
				else {
					viewStudentNames = viewStudentNames + Data.courseList.get(i).getCourseStudentNames().get(j) + ", ";
				}	
			}
			System.out.println("Student Names: " + viewStudentNames);
			
			// PRINT NUMBER OF STUDENTS REGISTERED
			System.out.println("Number of Registered Students: " + Data.courseList.get(i).getCourseCurrentNumberOfStudents());
			
			// PRINT MAX NUMBER OF STUDENTS ALLOWED
			System.out.println("Course Capacity: " + Data.courseList.get(i).getCourseCapacity() + " students");
		}
		
		this.menu();
	}
	
	public void registerInCourse () {
		// method takes in course name section number first/last name
		// create temporary reference of course c
		// using course info iterate thru main course list and find course
		// add c to student course list
		// find
		
		// REGISTERS STUDENT IN DESIRED COURSE
		
		System.out.println("Please enter your first name:");
		String registerFirstName = scanner.next();
		System.out.println("Please enter your last name:");
		String registerLastName = scanner.next();
		String registerFullName = registerFirstName + " " + registerLastName;
		scanner.nextLine();
		System.out.println("Please enter the course name of the class you want to register in:");
		String registerCourseName = scanner.nextLine();
		System.out.println("Please enter the section number of the class you want to register in:");
		String registerSectionNumber = scanner.next();
		

		Course newCourse;
		for (int i = 0; i < Data.courseList.size(); i++) {
			if (registerCourseName.equals(Data.courseList.get(i).getCourseName())) {
				if (registerSectionNumber.equals(Data.courseList.get(i).getCourseSectionNumber())) {
					newCourse = Data.courseList.get(i);
					studentCourseList.add (newCourse);
					Data.courseList.get(i).getCourseStudentNames().add(registerFullName);
					Data.courseList.get(i).setCourseCurrentNumberOfStudents(Data.courseList.get(i).getCourseCurrentNumberOfStudents() + 1);
					System.out.println("COURSE ADDED");
				}
			}
		}
		this.menu();
	}
	
	public void withdrawFromCourse () {
		
		// student will be asked to enter their name and the course name, then the name of the student will be taken off from given course list
		
		System.out.println("Please enter your first name:");
		scanner.nextLine();
		String withdrawFirstName = scanner.next();
		System.out.println("Please enter your last name:");
		String withdrawLastName = scanner.next();
		String withdrawFullName = withdrawFirstName + " " + withdrawLastName;
		scanner.nextLine();
		System.out.println("Please enter the course name of the course you wish to withdraw from:");
		String withdrawCourseName = scanner.nextLine();
		System.out.println("Please enter the course section number of the course you wish to withdraw from:");
		String withdrawCourseSectionNumber = scanner.next();
		
		for (int i = 0; i < Data.courseList.size(); i++) {
			if (withdrawCourseName.equals(Data.courseList.get(i).getCourseName())) {
				if (withdrawCourseSectionNumber.equals(Data.courseList.get(i).getCourseSectionNumber())) {
					for (int m = 0; m < this.studentCourseList.size(); m++) {
						if (Data.courseList.get(i).getCourseName().equals(studentCourseList.get(m).getCourseName()))
							studentCourseList.remove(m);
					}
					for (int j = 0; i < Data.courseList.get(i).getCourseStudentNames().size(); j++) {
						if (withdrawFullName.equals (Data.courseList.get(i).getCourseStudentNames().get(j))) {
							Data.courseList.get(i).getCourseStudentNames().remove(j);
						}
					}
					Data.courseList.get(i).getCourseStudentNames().remove(withdrawFullName);
					Data.courseList.get(i).setCourseCurrentNumberOfStudents(Data.courseList.get(i).getCourseCurrentNumberOfStudents() - 1);
					System.out.println("You have withdrawn from " + Data.courseList.get(i).getCourseName());
				}
			}
		}
		this.menu();
	}
	
	public void viewMyCourses () {
		
		// PRINTS OUT LOGGED IN STUDENTS
		
		System.out.println("My Courses:");
		
		for (int i = 0; i < this.studentCourseList.size(); i++) {
			System.out.println ("  " + studentCourseList.get(i).getCourseName());
		}
		this.menu();
	}
	
	public void viewOpenCourses () {
		
		// PRINTS OUT LIST OF OPEN COURSES
		
		System.out.println("Open Courses:");
		
		for (int i = 0; i < Data.courseList.size(); i++) {
			if (Data.courseList.get(i).getCourseCurrentNumberOfStudents() < Data.courseList.get(i).getCourseCapacity()) {
				System.out.println("  " + Data.courseList.get(i).getCourseName());
			}
		}
		this.menu();
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}
}