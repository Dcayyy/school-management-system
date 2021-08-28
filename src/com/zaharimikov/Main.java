package com.zaharimikov;

import dbmodel.Account;
import dbmodel.DataSource;
import account.User;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DataSource dataSource = new DataSource();

        if (!dataSource.open()) {
            System.out.println("Can't open dataSource");
            return;
        }

        mainMenu();

        dataSource.close();
    }

    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Student Management System");
        String answer = "";

        while (true) {
            System.out.println("Do you have an account? (yes/no)");
            answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.println("Wrong input!");
            }
        }

        Account account = new Account();
        User loggedUser = new User();

        if (answer.equalsIgnoreCase("no")) {
            while (true) {
                User zahari = account.createNewUser();
                if (account.register(zahari)) {
                    loggedUser = account.login();
                    break;
                }
            }
        } else if (answer.equalsIgnoreCase("yes")) {
            loggedUser = account.login();
        }

        account.LoggedUserFunctionalities(loggedUser);
    }
}