package pl.coderslab;

import java.util.Scanner;

public class UserSystem {

    public static void main(String[] args) {

        System.out.println("Welcome to User System!");

        String instruction = "Please specify your action (add/view/quit)...";
        System.out.println(instruction);

        Scanner scan = new Scanner(System.in);
        SolutionDao solutionDao = new SolutionDao();

        String action = scan.nextLine();

        while (!action.equals("quit")) {
            switch (action) {
                case "add":
                    System.out.println("Add solution action selected");

                    addSolution(scan, solutionDao, Integer.parseInt(args[0]));

                    System.out.println(instruction);
                    action = scan.nextLine();
                    break;

                case "view":
                    System.out.println("View my solutions action selected");

                    viewSolutions(solutionDao, Integer.parseInt(args[0]));

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
        System.out.println("Quit! Thanks for using User System!");
        scan.close();
    }

    private static void addSolution(Scanner scan, SolutionDao solutionDao, int userid) {
        ExerciseDao exerciseDao = new ExerciseDao();
        UserDao userDao = new UserDao();
        Exercise[] exercises = exerciseDao.findAll();

        Exercise[] exercisesToDo = new Exercise[0];
        for (Exercise exercise : exercises) {
            Solution[] solutions = solutionDao.findAllByExercise(exercise.getId());
            boolean solutionNotAdded = true;
            for (Solution solution : solutions) {
                if (solution.getUser().getId() == userid) {
                    solutionNotAdded = false;
                }
            }
            if (solutionNotAdded) {
                exercisesToDo = exerciseDao.addToArray(exercise, exercisesToDo);
            }
        }

        if(exercisesToDo.length == 0){
            System.out.println("There are no new exercises to do. You can relax :)");
            return;
        }
        System.out.println("List of all exercises without your solution:");
        for (Exercise exercise : exercisesToDo) {
            System.out.println(exercise);
        }

        System.out.println("Please provide exercise_id for which you would like to add a solution...");
        int exerciseId = getNumber(scan);
        scan.nextLine();

        while(!isExerciseIdOnExerciseList(exerciseId, exercisesToDo)) {
            System.out.println("Please provide correct exercise_id for which you would like to add a solution...");
            exerciseId = getNumber(scan);
            scan.nextLine();
        }

        Solution solution = new Solution();
        System.out.println("Please provide description...");
        solution.setDescription(scan.nextLine());


        User user = userDao.read(userid);
        solution.setUser(user);

        Exercise exercise = exerciseDao.read(exerciseId);
        solution.setExcercise(exercise);

        solutionDao.create(solution);
        System.out.println("Solution added successfully!");
    }

    private static void viewSolutions (SolutionDao solutionDao, int userId) {
        Solution[] solutions = solutionDao.findAllByUser(userId);
        System.out.println("Your solutions:");
        for (Solution solution : solutions) {
            System.out.println(solution);
        }
    }

    private static int getNumber(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.println("Please provide a numeric value");
            scan.next();
        }
        return scan.nextInt();
    }

    private static boolean isExerciseIdOnExerciseList(int exerciseId, Exercise[] exercises) {
        for (Exercise exercise : exercises) {
            if (exercise.getId() == exerciseId) {
                return true;
            }
        }
        return false;
    }
}

