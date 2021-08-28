package account;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Director extends User {
    private int directorId;
    private String firstName;
    private String lastName;
    private String type;
    private int userId;

    public Director(String firstName, String lastName, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
    }

    public Director() { };

    public void resetDirectorId() {
        this.directorId = 0;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDirectorId() {
        return directorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getType() {
        return type;
    }

    public int getUserId() {
        return userId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
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

    public void setType(String type) {
        this.type = type;
    }

    public String validateType() {
        Scanner scanner = new Scanner(System.in);

        String type = "";

        String regex = "^[a-zA-Z]+";

        Pattern pattern = Pattern.compile(regex);

        while (true) {
            System.out.print("Type: ");
            type = scanner.nextLine();

            Matcher matcher = pattern.matcher(type);

            if (matcher.matches()) {
                return type;
            }
            System.out.println("Wrong input");
        }
    }

    @Override
    public String toString() {
        return "Director{" +
                "directorId=" + directorId +
                "\n firstName='" + firstName + '\'' +
                "\n lastName='" + lastName + '\'' +
                "\n type='" + type + '\'' +
                "\n userId=" + userId +
                '}';
    }
}
