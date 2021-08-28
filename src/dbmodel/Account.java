package dbmodel;

import account.User;
import java.util.Scanner;

public class Account {

    DataSource dataSource;

    public Account() {
        dataSource = new DataSource();
        if (!dataSource.open())  {
            System.out.println("Can't open database");
        }
    }

    public User createNewUser() {
        System.out.println("***** Register *****");

        User user = new User();

        String emailAddress = user.validateEmailAddress();
        String accountType = user.validateAccountType();
        String password = user.validatePassword();

        user.setEmailAddress(emailAddress);
        user.setAccountType(accountType);
        user.setPassword(password);

        return user;
    }

    public boolean register(User user) {
        try {
            if (dataSource.insertUserIntoUsersTable(user)) {
                return true;
            }
        } catch (NullPointerException e) {
            System.out.println("null : " + e.getMessage());
            return false;
        }
        return false;
    }

    public User login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("***** Login *****");

        String username = "";
        String password = "";
        int attemptCounter = 0;

        User loggedUser;

        while (true) {
            System.out.print("Username: ");
            username = scanner.nextLine();

            System.out.print("Password: ");
            password = scanner.nextLine();

            if (!dataSource.checkLoginCredentials(username, password)) {
                System.out.println("Incorrect username or password.");
                attemptCounter++;
                if (attemptCounter == 3) {
                    System.out.println("You have to wait 3 minutes before attempting to login again.");
                    try {
                        Thread.sleep(18000);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else {
                loggedUser = dataSource.getLoggedUser(username, password);
                break;
            }
        }

        System.out.println("Successfully logged in.");
        return loggedUser;
    }

    public synchronized void LoggedUserFunctionalities(User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello " + user.getEmailAddress());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {

            System.out.println("Your functionalities are: ");
            System.out.println(
                    "1. Get ID\n" +
                    "2. Get email address\n" +
                    "3. Get account type\n" +
                    "4. Get password\n" +
                    "5. Change password\n" +
                    "6. Delete your account.");

            System.out.println("Enter 'exit' to exit the program");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("exit")) {
                break;
            }

            switch (choice) {
                case "1":
                    System.out.println("Your ID is: " + user.getUserId() + "\n");
                    break;
                case "2":
                    System.out.println("Your email address is: " + user.getEmailAddress() + "\n");
                    break;
                case "3":
                    System.out.println("Your account type is: " + user.getAccountType() + "\n");
                    break;
                case "4":
                    System.out.println("Your password is: " + user.getPassword() + "\n");
                    break;
                case "5":
                    dataSource.changePassword(user);
                    break;
                case "6":
                    dataSource.deleteUserFromUsersTable(user);
                    return;
                default:
                    System.out.println("There is not such option!");
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
