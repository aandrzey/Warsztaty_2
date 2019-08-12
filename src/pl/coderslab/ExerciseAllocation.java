package pl.coderslab;

import java.util.Scanner;

import static pl.coderslab.ExerciseManagement.displayAllExercises;
import static pl.coderslab.UserManagement.displayAllUsers;

public class ExerciseAllocation {

    public static void main(String[] args) {

        System.out.println("Welcome to Exercise Allocation System!");
        SolutionDao solutionDao = new SolutionDao();

        String instruction = "Please specify your action (add/view/quit)...";
        System.out.println(instruction);
        Scanner scan = new Scanner(System.in);

        String action = scan.nextLine();

        while (!action.equals("quit")) {
            switch (action) {
                case "add":
                    System.out.println("Allocate exercise action selected");

                    allocateSolution(scan, solutionDao);

                    System.out.println(instruction);
                    action = scan.nextLine();
                    break;

                case "view":
                    System.out.println("View user's exercises action selected");

                    viewUserSolutions(scan, solutionDao);

                    System.out.println(instruction);
                    action = scan.nextLine();
                    break;

                default:
                    System.out.println("Unknown action");

                    System.out.println(instruction);
                    action = scan.nextLine();
                    break;
            }
        }
        System.out.println("Quit! Thanks for using Exercise Allocation System!");
        scan.close();
    }


    private static void allocateSolution(Scanner scan, SolutionDao solutionDao) {
        UserDao userDao = new UserDao();
        displayAllUsers(userDao);
        System.out.println("Please provide user Id...");
        int userId = getNumber(scan);
        scan.nextLine();
        User user = userDao.read(userId);

        ExerciseDao exerciseDao = new ExerciseDao();
        displayAllExercises(exerciseDao);
        System.out.println("Please provide exercise Id...");
        int excerciseId = getNumber(scan);
        scan.nextLine();
        Exercise exercise = exerciseDao.read(excerciseId);

        Solution solution = new Solution();
        solution.setExcercise(exercise);
        solution.setUser(user);

        solutionDao.create(solution);
        System.out.println("Exercise allocated successfully!");
    }

    private static void viewUserSolutions(Scanner scan, SolutionDao solutionDao) {
        System.out.println("Please provide user Id...");
        int userId = getNumber(scan);
        scan.nextLine();

        Solution[] solutions = solutionDao.findAllByUser(userId);

        System.out.println("List od user's solutions:");
        for (Solution solution : solutions) {
            System.out.println(solution);
        }
    }


    private static int getNumber (Scanner scan) {
        while(!scan.hasNextInt()) {
            System.out.println("Please provide a numeric value");
            scan.next();
        }
        return scan.nextInt();
    }
}
