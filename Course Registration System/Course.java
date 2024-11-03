import java.util.ArrayList;
import java.io.Serializable;

public class Course implements Comparable <Course>, Serializable {
	
	private static final long serialVersionUID = -2914554172464198262L;

	private String courseName;
	
	private String courseID;
	
	private int courseCapacity;
	
	private int courseCurrentNumberOfStudents;
	
	private String courseInstructor;
	
	private String courseSectionNumber;
	
	private String courseLocation;
	
	ArrayList <String> courseStudentNames = new ArrayList <String>();
	
	// constructor used to create course object, given information from MyUniversityCourses.csv
	
	public Course (String [] myUniversityCourses) {
	
		//AUTOMATIC SHIFT TO SETTER METHOD... CHANGE IF WANT
		this.courseName = myUniversityCourses [0];
		this.setCourseID(myUniversityCourses [1]);
		this.courseCapacity = Integer.valueOf(myUniversityCourses [2]);
		this.courseCurrentNumberOfStudents = 0;
		this.courseInstructor = myUniversityCourses [5];
		this.setCourseSectionNumber(myUniversityCourses [6]);
		this.courseLocation = myUniversityCourses [7];
	}
	
	// constructor for course when course is created by admin
	
	public Course (String courseName, String courseID, String courseCapacity, String courseInstructor, String courseSectionNumber, String courseLocation) {
		
		this.courseName = courseName;
		this.setCourseID(courseID);
		this.courseCapacity = Integer.valueOf(courseCapacity);
		this.courseCurrentNumberOfStudents = 0;
		this.courseInstructor = courseInstructor;
		this.setCourseSectionNumber(courseSectionNumber);
		this.courseLocation = courseLocation;
	}
	
	public int compareTo (Course c1) {
		if (this.getCourseCurrentNumberOfStudents() > c1.getCourseCurrentNumberOfStudents())
			return 1;
		else if (this.getCourseCurrentNumberOfStudents() < c1.getCourseCurrentNumberOfStudents())
			return -1;
		else
			return 0;
	}

	public String getCourseID() {
		return this.courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getCourseSectionNumber() {
		return this.courseSectionNumber;
	}

	public void setCourseSectionNumber(String courseSectionNumber) {
		this.courseSectionNumber = courseSectionNumber;
	}

	public void setCourseCapacity (int courseCapacity) {
		this.courseCapacity = courseCapacity;
	}

	public void setCourseInstructor(String courseInstructor) {
		this.courseInstructor = courseInstructor;
	}

	public void setCourseLocation(String courseLocation) {
		this.courseLocation = courseLocation;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public int getCourseCapacity() {
		return this.courseCapacity;
	}

	public int getCourseCurrentNumberOfStudents() {
		return this.courseCurrentNumberOfStudents;
	}
	
	public void setCourseCurrentNumberOfStudents(int update) {
		this.courseCurrentNumberOfStudents = update;
	}

	public String getCourseInstructor() {
		return this.courseInstructor;
	}

	public String getCourseLocation() {
		return this.courseLocation;
	}
	
	public void setCourseStudentNames (Student student) {
		
		this.courseStudentNames.add(student.getFirstName(student) + " " + student.getLastName(student));
	}

	public boolean equals (Course course) {
		
		if (this.courseName.equals(course.courseName))
			return true;
		
		return false;
	}
	public ArrayList <String> getCourseStudentNames() {
		return this.courseStudentNames;
	}
}
