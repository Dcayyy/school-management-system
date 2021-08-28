package account;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student extends User {
    private int studentId = 0;
    private String firstName;
    private String lastName;
    private int age;
    private int numberInClass;
    private String speciality;
    private String classroom;
    private int user_id;

    public Student(String firstName, String lastName, int age,
                   int numberInClass, String speciality, String classroom) {
        studentId++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.numberInClass = numberInClass;
        this.speciality = speciality;
        this.classroom = classroom;
    }

    public Student() {
        studentId++;
    }

    public void resetStudentId() {
        studentId = 0;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getNumberInClass() {
        return numberInClass;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String validateFirstName() {
        return super.validateFirstName();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String validateLastName() {
        return super.validateLastName();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int validateAge() {
        Scanner scanner = new Scanner(System.in);
        int age;

        while (true) {
            System.out.print("Enter age: ");
            age = scanner.nextInt();
            if (age >= 14 && age <= 19) {
                return age;
            }
            System.out.println("Wrong input! You can enter age only 14-19 including.");
        }
    }

    public void setNumberInClass(int numberInClass) {
        this.numberInClass = numberInClass;
    }

    public int validateNumberOfClass() {
        Scanner scanner = new Scanner(System.in);
        int numberOfClass;

        while (true) {
            System.out.print("Enter number in class: ");
            numberOfClass = scanner.nextInt();
            if (numberOfClass >= 1 && numberOfClass <= 27) {
                return numberOfClass;
            }
            System.out.println("Wrong input! You can enter number only in 1-27 including.");
        }

    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String validateSpeciality() {
        Scanner scanner = new Scanner(System.in);
        String speciality;

        while (true) {
            System.out.print("Enter speciality: ");
            speciality = scanner.nextLine();
            switch (speciality.toLowerCase()) {
                case "software developer":
                    return "Software Developer";
                case "application developer":
                    return "Application Developer";
                case "mechatronics":
                    return "Mechatronics";
                case "computer equipment and technologies":
                    return "Computer Equipment and Technologies";
                case "e-commerce":
                    return "Ecommerce";
                case "advertising graphics":
                    return "Advertising Graphics";
                case "organization of tourism and leisure":
                    return "Organization of Tourism and Leisure";
                case "manufacture of culinary products and beverages":
                    return "Manufacture of Culinary Products and Beverages";
                case "production of bread and bakery products":
                    return "Production of Bread and Bakery Products";
                default:
                    System.out.println("There is no such speciality!\n");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println("Thread failed in validateSpeciality() : " + e.getMessage());
                    }
            }
        }
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String validateClassroom() {
        Scanner scanner = new Scanner(System.in);

        String classroom;

        String regex = "^[0-9]{1,2}[a-zA-Z]";

        Pattern pattern = Pattern.compile(regex);

        while (true) {
            System.out.print("Classroom: ");
            classroom = scanner.nextLine();

            Matcher matcher = pattern.matcher(classroom);

            if (matcher.matches()) {
                return classroom;
            }
            System.out.println("Wrong Input!");
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                "\n firstName='" + firstName + '\'' +
                "\n lastName='" + lastName + '\'' +
                "\n age=" + age +
                "\n numberInClass=" + numberInClass +
                "\n speciality='" + speciality + '\'' +
                "\n classroom='" + classroom + '\'' +
                '}';
    }
}
