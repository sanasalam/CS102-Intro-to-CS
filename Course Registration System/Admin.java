import java.util.List;
import java.util.Collections;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

// 
// Decompiled by Procyon v0.5.36
// 

public class Admin extends User implements AdminInterface
{
    static Scanner scanner;
    
    static {
        Admin.scanner = new Scanner(System.in);
    }
    
    public Admin(final String username, final String password, final String firstName, final String lastName) {
        super(username, password, firstName, lastName);
    }
    
    public void menu() {
        System.out.println("C    Course Management");
        System.out.println("R    Reports");
        System.out.println("L    Log Out");
        System.out.println("Please enter the letter that corresponds with what you want to do:");
        final String firstInput = Admin.scanner.next();
        if (firstInput.equals("C")) {
            System.out.println("C    Create Course");
            System.out.println("D    Delete Course");
            System.out.println("E    Edit Course");
            System.out.println("V    View Course Information");
            System.out.println("R    Register Student");
            System.out.println("L    Log Out");
            System.out.println("Please enter the letter that corresponds with what you want to do:");
            final String secondInput = Admin.scanner.next();
            if (secondInput.equals("C")) {
                this.createCourse();
            }
            else if (secondInput.equals("D")) {
                this.deleteCourse();
            }
            else if (secondInput.equals("E")) {
                this.editCourse();
            }
            else if (secondInput.equals("V")) {
                this.displayCourseInfo();
            }
            else if (secondInput.equals("R")) {
                this.registerStudent();
            }
            else if (secondInput.equals("L")) {
                CourseRegistrationSystem.logOut();
            }
        }
        else if (firstInput.equals("R")) {
            System.out.println("V    View Courses");
            System.out.println("F    View Full Courses");
            System.out.println("E    Export Full Courses");
            System.out.println("C    View Course Students");
            System.out.println("S    View Student Courses");
            System.out.println("O    Order Courses by Number of Students Registered");
            System.out.println("L    Log Out");
            System.out.println("Please enter the letter that corresponds with what you want to do:");
            final String thirdInput = Admin.scanner.next();
            if (thirdInput.equals("V")) {
                this.viewCourses();
            }
            else if (thirdInput.equals("F")) {
                this.viewFullCourses();
            }
            else if (thirdInput.equals("E")) {
                this.writeFullCourses();
            }
            else if (thirdInput.equals("C")) {
                this.viewCourseStudents();
            }
            else if (thirdInput.equals("S")) {
                this.viewStudentCourses();
            }
            else if (thirdInput.equals("O")) {
                this.sortCourses();
            }
            else if (thirdInput.equals("L")) {
                CourseRegistrationSystem.logOut();
            }
        }
        else if (firstInput.equals("L")) {
            CourseRegistrationSystem.logOut();
        }
    }
    
    public void viewCourses() {
        System.out.println("Below is information on each course in the system.");
        for (int i = 0; i < Data.courseList.size(); ++i) {
            System.out.println(Data.courseList.get(i).getCourseName());
            String viewStudentNames = "";
            for (int j = 0; j < Data.courseList.get(i).getCourseStudentNames().size(); ++j) {
                if (j == Data.courseList.get(i).getCourseStudentNames().size() - 1) {
                    viewStudentNames = String.valueOf(viewStudentNames) + Data.courseList.get(i).getCourseStudentNames().get(j);
                }
                else {
                    viewStudentNames = String.valueOf(viewStudentNames) + Data.courseList.get(i).getCourseStudentNames().get(j) + ", ";
                }
            }
            System.out.println("Student Names: " + viewStudentNames);
            System.out.println("Number of Registered Students: " + Data.courseList.get(i).getCourseCurrentNumberOfStudents());
            System.out.println("Course Capacity: " + Data.courseList.get(i).getCourseCapacity() + " students");
        }
        this.menu();
    }
    
    public void createCourse() {
        System.out.println("You have chosen to create a course.");
        System.out.println("Please enter the new course ID: ");
        final String inputCourseID = Admin.scanner.next();
        Admin.scanner.nextLine();
        System.out.println("Please enter the new course name: ");
        final String inputCourseName = Admin.scanner.nextLine();
        System.out.println("Please enter the new course capacity: ");
        final String inputCourseCapacity = Admin.scanner.next();
        Admin.scanner.nextLine();
        System.out.println("Please enter the new course instructor: ");
        final String inputCourseInstructor = Admin.scanner.nextLine();
        System.out.println("Please enter the new course section number: ");
        final String inputCourseSectionNumber = Admin.scanner.next();
        Admin.scanner.nextLine();
        System.out.println("Please enter the new course location: ");
        final String inputCourseLocation = Admin.scanner.nextLine();
        final Course newCourse = new Course(inputCourseName, inputCourseID, inputCourseCapacity, inputCourseInstructor, inputCourseSectionNumber, inputCourseLocation);
        Data.courseList.add(newCourse);
        System.out.println("Course Created");
        this.menu();
    }
    
    public void deleteCourse() {
        System.out.println("You have chosen to delete a course");
        System.out.println("Please enter the ID of the course you wish to delete: ");
        final String deleteCourseID = Admin.scanner.next();
        System.out.println("Please enter the section number of the course you wish to delete: ");
        final String deleteCourseSectionNumber = Admin.scanner.next();
        for (int i = 0; i < Data.courseList.size(); ++i) {
            if (deleteCourseID.equals(Data.courseList.get(i).getCourseID()) && deleteCourseSectionNumber.equals(Data.courseList.get(i).getCourseSectionNumber())) {
                Data.courseList.remove(i);
            }
        }
        System.out.println("Course Deleted");
        this.menu();
    }
    
    public void editCourse() {
        System.out.println("You have chosen to edit a course.");
        System.out.println("Please enter the ID of the course you wish to delete: ");
        final String editCourseID = Admin.scanner.next();
        System.out.println("Please enter the section number of the course you wish to edit: ");
        final String editCourseSectionNumber = Admin.scanner.next();
        for (int i = 0; i < Data.courseList.size(); ++i) {
            if (editCourseID.equals(Data.courseList.get(i).getCourseID()) && editCourseSectionNumber.equals(Data.courseList.get(i).getCourseSectionNumber())) {
                System.out.println("C    Course Capacity");
                System.out.println("I    Course Instructor");
                System.out.println("S    Course Section Number");
                System.out.println("L    Course Location");
                System.out.println("Please enter the letter that corresponds to the item you wish to edit: ");
                if (Admin.scanner.next().equals("C")) {
                    System.out.println("Please enter the new capacity: ");
                    Data.courseList.get(i).setCourseCapacity(Admin.scanner.nextInt());
                }
                else if (Admin.scanner.next().equals("I")) {
                    System.out.println("Please enter the new instructor: ");
                    Data.courseList.get(i).setCourseInstructor(Admin.scanner.next());
                }
                else if (Admin.scanner.next().equals("S")) {
                    System.out.println("Please enter the new section number: ");
                    Data.courseList.get(i).setCourseSectionNumber(Admin.scanner.next());
                }
                else if (Admin.scanner.next().equals("L")) {
                    System.out.println("Please enter the new location: ");
                    Data.courseList.get(i).setCourseLocation(Admin.scanner.next());
                }
                else {
                    System.out.println("Sorry, you entered an invalid letter.");
                }
            }
        }
        System.out.println("Course Updated");
        this.menu();
    }
    
    public void displayCourseInfo() {
        System.out.println("Please enter the ID for the course you wish to display: ");
        final String displayCourseID = Admin.scanner.next();
        for (int i = 0; i < Data.courseList.size(); ++i) {
            if (displayCourseID.equals(Data.courseList.get(i).getCourseID())) {
                System.out.println("Course Name: " + Data.courseList.get(i).getCourseName());
                System.out.println("Course Capacity: " + Data.courseList.get(i).getCourseCapacity() + " students");
                System.out.println("Current Number of Students: " + Data.courseList.get(i).getCourseCurrentNumberOfStudents());
                System.out.println("Enrolled Students: ");
                for (int j = 0; j < Data.courseList.get(i).getCourseStudentNames().size(); ++j) {
                    System.out.println(Data.courseList.get(i).getCourseStudentNames().get(j));
                }
                System.out.println("Course Instructor: " + Data.courseList.get(i).getCourseInstructor());
                System.out.println("Course Location: " + Data.courseList.get(i).getCourseLocation());
            }
        }
        this.menu();
    }
    
    public void registerStudent() {
        System.out.println("You have chosen to register a student.");
        System.out.println("Please enter the student's first name: ");
        final String newStudentFirstName = Admin.scanner.next();
        System.out.println("Please enter the student's last name: ");
        final String newStudentLastName = Admin.scanner.next();
        System.out.println("Please enter the student's username: ");
        final String newStudentUsername = Admin.scanner.next();
        System.out.println("Please enter the student's password: ");
        final String newStudentPassword = Admin.scanner.next();
        final Student newStudent = new Student(newStudentUsername, newStudentPassword, newStudentFirstName, newStudentLastName);
        Data.studentList.add(newStudent);
        System.out.println("Student Added");
        this.menu();
    }
    
    public void viewFullCourses() {
        System.out.println("The following courses are FULL:");
        for (int i = 0; i < Data.courseList.size(); ++i) {
            if (Data.courseList.get(i).getCourseCurrentNumberOfStudents() == Data.courseList.get(i).getCourseCapacity()) {
                System.out.println(Data.courseList.get(i).getCourseName());
            }
        }
        this.menu();
    }
    
    public void writeFullCourses() {
        final String fileName = "fullCourses.txt";
        try {
            final FileWriter fileWriter = new FileWriter(fileName);
            final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < Data.courseList.size(); ++i) {
                if (Data.courseList.get(i).getCourseCurrentNumberOfStudents() == Data.courseList.get(i).getCourseCapacity()) {
                    bufferedWriter.write(Data.courseList.get(i).getCourseName());
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
            fileWriter.close();
            System.out.println("Full courses have been written to file.");
        }
        catch (IOException exk) {
            System.out.println("Error writing file '" + fileName + "'");
            exk.printStackTrace();
        }
        this.menu();
    }
    
    public void viewCourseStudents() {
        System.out.println("You have chosen to view the students registered in a specific course.");
        System.out.println("Please enter the ID of the course: ");
        final String verifyCourseID = Admin.scanner.next();
        System.out.println("Please enter the section number of the course: ");
        final String verifyCourseSectionNumber = Admin.scanner.next();
        for (int i = 0; i < Data.courseList.size(); ++i) {
            if (verifyCourseID.equals(Data.courseList.get(i).getCourseID()) && verifyCourseSectionNumber.equals(Data.courseList.get(i).getCourseSectionNumber())) {
                System.out.println("Students in " + Data.courseList.get(i).getCourseName());
                for (int j = 0; j < Data.courseList.get(i).getCourseStudentNames().size(); ++j) {
                    System.out.println(Data.courseList.get(i).getCourseStudentNames().get(j));
                }
            }
        }
        this.menu();
    }
    
    public void viewStudentCourses() {
        System.out.println("You have chosen to view a student's registered courses.");
        System.out.println("Please enter the student's first name: ");
        final String verifyFirstName = Admin.scanner.next();
        System.out.println("Please enter the student's last name: ");
        final String verifyLastName = Admin.scanner.next();
        for (int i = 0; i < Data.studentList.size(); ++i) {
            if (verifyFirstName.equals(this.getFirstName((User)Data.studentList.get(i))) && verifyLastName.equals(this.getLastName((User)Data.studentList.get(i)))) {
                System.out.println(String.valueOf(verifyFirstName) + " " + verifyLastName + " Courses:");
                for (int j = 0; j < Data.courseList.size(); ++j) {
                    for (int k = 0; k < Data.courseList.get(j).getCourseStudentNames().size(); ++k) {
                        final String verifyFullName = String.valueOf(verifyFirstName) + " " + verifyLastName;
                        if (verifyFullName.equals(Data.courseList.get(j).getCourseStudentNames().get(k))) {
                            System.out.println(Data.courseList.get(j).getCourseName());
                        }
                    }
                }
            }
        }
        this.menu();
    }
    
    public void sortCourses() {
        System.out.println("You have chosen to sort the courses based on the current number of students registered");
        Collections.sort(Data.courseList);
        for (int i = 0; i < Data.courseList.size(); ++i) {
            System.out.println(String.valueOf(Data.courseList.get(i).getCourseName()) + " " + Data.courseList.get(i).getCourseCurrentNumberOfStudents());
        }
        this.menu();
    }
}
