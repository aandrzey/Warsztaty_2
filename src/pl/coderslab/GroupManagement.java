package pl.coderslab;

import java.util.Scanner;

public class GroupManagement {
    public static void main(String[] args) {

        System.out.println("Welcome to Group Management System!");
        UserGroupDao userGroupDao = new UserGroupDao();

        displayAllGroups(userGroupDao);

        String instruction = "Please specify your action (add/edit/delete/quit)...";
        System.out.println(instruction);
        Scanner scan = new Scanner(System.in);

        String action = scan.nextLine();

        while (!action.equals("quit")) {
            switch (action) {
                case "add":
                    System.out.println("Add group action selected");

                    createGroup(scan, userGroupDao);

                    displayAllGroups(userGroupDao);
                    System.out.println(instruction);
                    action = scan.nextLine();
                    break;

                case "edit":
                    System.out.println("Edit user action selected");

                    editGroup(scan, userGroupDao);

                    displayAllGroups(userGroupDao);
                    System.out.println(instruction);
                    action = scan.nextLine();
                    break;

                case "delete":
                    System.out.println("Delete user action selected");

                    deleteGroup(scan, userGroupDao);

                    displayAllGroups(userGroupDao);
                    System.out.println(instruction);
                    action = scan.nextLine();
                    break;

                default:
                    System.out.println("Unknown action");

                    displayAllGroups(userGroupDao);
                    System.out.println(instruction);
                    action = scan.nextLine();
                    break;
            }
        }
        System.out.println("Quit! Thanks for using Group Management System!");
        scan.close();
    }


    private static void displayAllGroups(UserGroupDao userGroupDao) {
        System.out.println("List of all groups:");
        UserGroup[] groups = userGroupDao.findAll();
        for (UserGroup group : groups) {
            System.out.println(group);
        }
    }

    private static void createGroup(Scanner scan, UserGroupDao userGroupDao) {
        UserGroup group = new UserGroup();
        System.out.println("Please provide name...");
        group.setName(scan.nextLine());

        userGroupDao.create(group);
        System.out.println("Group added successfully!");
    }

    private static void editGroup(Scanner scan, UserGroupDao userGroupDao) {
        System.out.println("Please provide group Id...");
        UserGroup group = userGroupDao.read(getNumber(scan));
        scan.nextLine();
        System.out.println("Please provide name...");
        group.setName(scan.nextLine());

        userGroupDao.update(group);
        System.out.println("Group edited successfully!");
    }

    private static void deleteGroup(Scanner scan, UserGroupDao userGroupDao) {
        System.out.println("Please provide group Id...");
        int groupId = getNumber(scan);
        scan.nextLine();

        userGroupDao.delete(groupId);
        System.out.println("Group deleted successfully!");
    }

    private static int getNumber(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.println("Please provide a numeric value");
            scan.next();
        }
        return scan.nextInt();
    }
}
