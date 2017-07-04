package ch10;



public class Course implements Cloneable{

    private String courseName;
    private String[] students = new String[4];
    private int numberOfStudents;

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public void addStudent(String student) {
        students[numberOfStudents] = student;
        numberOfStudents++;
    }

    public String[] getStudents() {
        return students;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getCourseName() {
        return courseName;
    }

    public void dropStudent(String student) {
        // Left as an exercise in Exercise 10.9
    }

    @Override
    public Object clone() {

        Course course = null;

        try {
            course = (Course) super.clone();
            course.students = students.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


        return course;
    }


    public static void main(String[] args) throws CloneNotSupportedException {
        Course course1 = new Course("math");
        course1.addStudent("ana");
        course1.addStudent("ion");

        Course course2 = (Course ) course1.clone();
        course2.addStudent("alex");
        course1.setCourseName("physics");

        System.out.println("Course1: " + course1.getCourseName());
        for(String student:course1.getStudents())
            System.out.println(student);

        System.out.println("Course2:" + course2.getCourseName());

        for(String student:course2.getStudents())
            System.out.println(student);

        System.out.println("Course1:" + course1.getCourseName());
        for(String student:course1.getStudents())
            System.out.println(student);
    }
}

