package pl.coderslab;

import java.util.Scanner;

public class ExerciseManagement {

    public static void main(String[] args) {

        System.out.println("Welcome to Exercise Management System!");
        ExerciseDao exerciseDao = new ExerciseDao();

        displayAllExercises(exerciseDao);

        String instruction = "Please specify your action (add/edit/delete/quit)...";
        System.out.println(instruction);
        Scanner scan = new Scanner(System.in);

        String action = scan.nextLine();

        while (!action.equals("quit")) {
            switch (action) {
                case "add":
                    System.out.println("Add exercise action selected");

                    createExercise(scan, exerciseDao);

                    displayAllExercises(exerciseDao);
                    System.out.println(instruction);
                    action = scan.nextLine();
                    break;

                case "edit":
                    System.out.println("Edit exercise action selected");

                    editExercise(scan, exerciseDao);

                    displayAllExercises(exerciseDao);
                    System.out.println(instruction);
                    action = scan.nextLine();
                    break;

                case "delete":
                    System.out.println("Delete exercise action selected");

                    deleteExercise(scan, exerciseDao);

                    displayAllExercises(exerciseDao);
                    System.out.println(instruction);
                    action = scan.nextLine();
                    break;

                default:
                    System.out.println("Unknown action");

                    displayAllExercises(exerciseDao);
                    System.out.println(instruction);
                    action = scan.nextLine();
                    break;
            }
        }
        System.out.println("Quit! Thanks for using Exercise Management System!");
        scan.close();
    }


    protected static void displayAllExercises(ExerciseDao exerciseDao) {
        System.out.println("List of all exercises:");
        Exercise[] exercises = exerciseDao.findAll();
        for (Exercise exercise : exercises) {
            System.out.println(exercise);
        }
    }

    private static void createExercise(Scanner scan, ExerciseDao exerciseDao) {
        Exercise exercise = new Exercise();
        System.out.println("Please provide title...");
        exercise.setTitle(scan.nextLine());
        System.out.println("Please provide description...");
        exercise.setDescription(scan.nextLine());

        exerciseDao.create(exercise);
        System.out.println("Exercise added successfully!");
    }

    private static void editExercise(Scanner scan, ExerciseDao exerciseDao) {
        System.out.println("Please provide exercise Id...");
        Exercise exercise = exerciseDao.read(getNumber(scan));
        scan.nextLine();
        System.out.println("Please provide title...");
        exercise.setTitle(scan.nextLine());
        System.out.println("Please provide description...");
        exercise.setDescription(scan.nextLine());

        exerciseDao.update(exercise);
        System.out.println("Exercise edited successfully!");
    }

    private static void deleteExercise (Scanner scan, ExerciseDao exerciseDao) {
        System.out.println("Please provide exercise Id...");
        int exerciseId = getNumber(scan);
        scan.nextLine();

        exerciseDao.delete(exerciseId);
        System.out.println("Exercise deleted successfully!");
    }

    private static int getNumber (Scanner scan) {
        while(!scan.hasNextInt()) {
            System.out.println("Please provide a numeric value");
            scan.next();
        }
        return scan.nextInt();
    }
}
