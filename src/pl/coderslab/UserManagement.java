package pl.coderslab;

import java.util.Scanner;

public class UserManagement {

    public static void main(String[] args) {

        System.out.println("Welcome to User Management System!");
        UserDao userDao = new UserDao();

        displayAllUsers(userDao);

        String instruction = "Please specify your action (add/edit/delete/quit)...";
        System.out.println(instruction);
        Scanner scan = new Scanner(System.in);

        String action = scan.nextLine();

        while (!action.equals("quit")) {
            switch (action) {
                case "add":
                    System.out.println("Add user action selected");

                    createUser(scan, userDao);

                    displayAllUsers(userDao);
                    System.out.println(instruction);
                    action = scan.nextLine();
                    break;

                case "edit":
                    System.out.println("Edit user action selected");

                    editUser(scan, userDao);

                    displayAllUsers(userDao);
                    System.out.println(instruction);
                    action = scan.nextLine();
                    break;

                case "delete":
                    System.out.println("Delete user action selected");

                    deleteUser(scan, userDao);

                    displayAllUsers(userDao);
                    System.out.println(instruction);
                    action = scan.nextLine();
                    break;

                default:
                    System.out.println("Unknown action");

                    displayAllUsers(userDao);
                    System.out.println(instruction);
                    action = scan.nextLine();
                    break;
            }
        }
        System.out.println("Quit! Thanks for using User Management System!");
        scan.close();
    }


    private static void displayAllUsers(UserDao userDao) {
        System.out.println("List of all users:");
        User[] users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private static void createUser(Scanner scan, UserDao userDao) {
        User user = new User();
        System.out.println("Please provide username...");
        user.setUsername(scan.nextLine());
        System.out.println("Please provide email...");
        user.setEmail(scan.nextLine());
        System.out.println("Please provide password...");
        user.setPassword(scan.nextLine());
        System.out.println("Please provide group_id...");
        UserGroupDao userGroupDao = new UserGroupDao();
        UserGroup group = userGroupDao.read(getNumber(scan));
        scan.nextLine();
        user.setGroup(group);

        userDao.create(user);
        System.out.println("User added successfully!");
    }

    private static void editUser(Scanner scan, UserDao userDao) {
        System.out.println("Please provide user Id...");
        User user = userDao.read(getNumber(scan));
        scan.nextLine();
        System.out.println("Please provide username...");
        user.setUsername(scan.nextLine());
        System.out.println("Please provide email...");
        user.setEmail(scan.nextLine());
        System.out.println("Please provide password...");
        user.setPassword(scan.nextLine());
        System.out.println("Please provide group_id...");
        UserGroupDao userGroupDao = new UserGroupDao();
        UserGroup group = userGroupDao.read(getNumber(scan));
        scan.nextLine();
        user.setGroup(group);

        userDao.update(user);
        System.out.println("User edited successfully!");
    }

    private static void deleteUser (Scanner scan, UserDao userDao) {
        System.out.println("Please provide user Id...");
        int userId = getNumber(scan);
        scan.nextLine();

        userDao.delete(userId);
        System.out.println("User deleted successfully!");
    }

    private static int getNumber (Scanner scan) {
        while(!scan.hasNextInt()) {
            System.out.println("Please provide a numeric value");
            scan.next();
        }
        return scan.nextInt();
    }

}
