package account;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String accountType;

    public User() { }

    public User(int userId, String emailAddress, String password, String accountType) {
        this.userId = userId;
        this.emailAddress = emailAddress;
        this.password = password;
        this.accountType = accountType;
    }

    public void resetUserId() {
        this.userId = 0;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String validateFirstName() {
        Scanner scanner = new Scanner(System.in);

        String firstName = "";

        String regex = "[a-zA-Z]+";

        Pattern pattern = Pattern.compile(regex);

        while (true) {
            System.out.print("First name: ");
            firstName = scanner.nextLine();

            Matcher matcher = pattern.matcher(firstName);

            if (matcher.matches()) {
                return firstName;
            }
            System.out.println("Wrong input!");
        }
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String validateLastName() {
        Scanner scanner = new Scanner(System.in);

        String lastName = "";

        String regex = "[a-zA-Z]+";

        Pattern pattern = Pattern.compile(regex);

        while (true) {
            System.out.print("Last name: ");
            lastName = scanner.nextLine();

            Matcher matcher = pattern.matcher(lastName);

            if (matcher.matches()) {
                return lastName;
            }
            System.out.println("Wrong input!");
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String validatePassword() {
        Scanner scanner = new Scanner(System.in);

        String password = "";
        String repeatedPassword = "";

        while (true) {
            System.out.print("Password: ");
            password = scanner.nextLine();

            System.out.print("Repeat Password: ");
            repeatedPassword = scanner.nextLine();

            if (password.length() >= 6 && password.length() <= 16) {
                if (password.equals(repeatedPassword)) {
                    return password;
                }
            }
        }
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String validateEmailAddress() {
        Scanner scanner = new Scanner(System.in);

        String emailAddress = "";

        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";

        //initialize the Pattern object
        Pattern pattern = Pattern.compile(regex);

        while (true) {
            System.out.print("Email address: ");
            emailAddress = scanner.nextLine();

            //searching for occurrences of regex
            Matcher matcher = pattern.matcher(emailAddress);

            if (matcher.matches()) {
                return emailAddress;
            }
            System.out.println("Wrong Input!");
        }
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String validateAccountType() {
        Scanner scanner = new Scanner(System.in);

        String accountType = "";

        while (true) {
            System.out.print("Account type: ");
            accountType = scanner.nextLine();

            if (accountType.equalsIgnoreCase("Student") ||
                    accountType.equalsIgnoreCase("Teacher") ||
                    accountType.equalsIgnoreCase("Director")) {
                break;
            }
            System.out.println("Possible accounts are: 'Student, Teacher, Director'");
        }
        return accountType;
    }


    @Override
    public String toString() {
        return "User{" +
                ", emailAddress='" + emailAddress + '\'' +
                ", accountType='" + accountType + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
