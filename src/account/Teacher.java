package account;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Teacher extends User {
    private int teacherId = 0;
    private String firstName;
    private String lastName;
    private String taughtSubject;
    private String headOfClass;
    private int user_id;

    public Teacher() {
        teacherId++;
    }

    public Teacher(String firstName, String lastName,
                   String taughtSubject, String headOfClass) {
        this.teacherId++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.taughtSubject = taughtSubject;
        this.headOfClass = headOfClass;
    }

    public void resetTeacherId() {
        teacherId = 0;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTaughtSubject() {
        return taughtSubject;
    }

    public String getHeadOfClass() {
        return headOfClass;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public void setTaughtSubject(String taughtSubject) {
        this.taughtSubject = taughtSubject;
    }

    public String validateTaughtSubject() {
        Scanner scanner = new Scanner(System.in);

        String taughtSubject = "";

        String regex = "[a-zA-Z]+";

        Pattern pattern = Pattern.compile(regex);

        while (true) {
            System.out.print("Taught subject: ");
            taughtSubject = scanner.nextLine();

            Matcher matcher = pattern.matcher(taughtSubject);

            if (matcher.matches()) {
                return taughtSubject;
            }
            System.out.println("Wrong input!");
        }
    }

    public void setHeadOfClass(String headOfClass) {
        this.headOfClass = headOfClass;
    }

    public String validateHeadOfClass() {
        Scanner scanner = new Scanner(System.in);

        String headOfClass = "";

        String regex = "[0-9]{1,2}[a-zA-Z]";

        Pattern pattern = Pattern.compile(regex);

        while (true) {
            System.out.print("Head of the class: ");
            headOfClass = scanner.nextLine();

            Matcher matcher = pattern.matcher(headOfClass);

            if (matcher.matches()) {
                return headOfClass;
            }
            System.out.println("Wrong input!");
        }
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                "\n firstName='" + firstName + '\'' +
                "\n lastName='" + lastName + '\'' +
                "\n taughtSubject='" + taughtSubject + '\'' +
                "\n headOfClass='" + headOfClass + '\'' +
                '}';
    }
}
