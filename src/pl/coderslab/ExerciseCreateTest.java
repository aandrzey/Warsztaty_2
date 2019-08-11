package pl.coderslab;

public class ExerciseCreateTest {

    public static void main(String[] args) {

        Exercise exercise = new Exercise();
        exercise.setTitle("Rozwiązanie zadania 1");
        exercise.setDescription("Aby rozwiązać zadanie 1 wciśnij x");

        ExerciseDao exerciseDao = new ExerciseDao();
        exerciseDao.create(exercise);
    }
}
