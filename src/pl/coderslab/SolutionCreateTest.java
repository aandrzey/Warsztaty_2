package pl.coderslab;

public class SolutionCreateTest {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User user = userDao.read(4);

        System.out.println(user);

        ExerciseDao exerciseDao = new ExerciseDao();
        Exercise exercise = exerciseDao.read(1);

        System.out.println(exercise);

        Solution solution = new Solution();
        solution.setUser(user);
        solution.setExcercise(exercise);
        solution.setDescription("X is here.");

        SolutionDao solutionDao = new SolutionDao();
        solutionDao.create(solution);
    }

}
