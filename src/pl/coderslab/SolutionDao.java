package pl.coderslab;

import java.sql.*;
import java.util.Arrays;

public class SolutionDao {

    private static final String CREATE_SOLUTION_QUERY =
            "INSERT INTO solution(created, updated, description, exercise_id, users_id) VALUES (?, ?, ?, ?, ?)";
    private static final String READ_SOLUTION_QUERY =
            "SELECT * FROM solution where id = ?";
    private static final String UPDATE_SOLUTION_QUERY =
            "UPDATE users SET updated = ?, description = ?, exercise_id = ?, users_id = ? where id = ?";
    private static final String DELETE_SOLUTION_QUERY =
            "DELETE FROM solution WHERE id = ?";
    private static final String FIND_ALL_SOLUTIONS_QUERY =
            "SELECT * FROM solution";
    private static final String FIND_ALL_BY_USER_ID =
            "SELECT * FROM solution where users_id = ?";
    private static final String FIND_ALL_BY_EXERCISE_ID =
            "SELECT * FROM solution where exercise_id = ? ORDER BY created ASC";

    public Solution create(Solution solution) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, "current_timestamp()");
            statement.setString(2, "current_timestamp()");
            statement.setString(3, solution.getDescription());
            if (solution.getExcercise().getId() > 0) {
                statement.setInt(4, solution.getExcercise().getId());
            } else {
                throw new IllegalArgumentException("exercise_id nie może być mniejsze niż 0");
            }
            if (solution.getUser().getId() > 0) {
                statement.setInt(5, solution.getUser().getId());
            } else {
                throw new IllegalArgumentException("users_id nie może być mniejsze niż 0");
            }
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solution read(int solutionId) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                int exerciseId = resultSet.getInt("exercise_id");
                ExerciseDao exerciseDao = new ExerciseDao();
                solution.setExcercise(exerciseDao.read(exerciseId));
                int usersId = resultSet.getInt("users_id");
                UserDao userDao = new UserDao();
                solution.setUser(userDao.read(usersId));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Solution solution) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setString(1, "current_timestamp()");
            statement.setString(2, solution.getDescription());
            if (solution.getExcercise().getId() > 0) {
                statement.setInt(3, solution.getExcercise().getId());
            } else {
                throw new IllegalArgumentException("exercise_id nie może być mniejsze niż 0");
            }
            if (solution.getUser().getId() > 0) {
                statement.setInt(4, solution.getUser().getId());
            } else {
                throw new IllegalArgumentException("users_id nie może być mniejsze niż 0");
            }
            statement.setInt(5, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int solutionId) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Solution[] findAll() {
        try (Connection conn = DBUtil.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                int exerciseId = resultSet.getInt("exercise_id");
                ExerciseDao exerciseDao = new ExerciseDao();
                solution.setExcercise(exerciseDao.read(exerciseId));
                int usersId = resultSet.getInt("users_id");
                UserDao userDao = new UserDao();
                solution.setUser(userDao.read(usersId));
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Solution[] addToArray(Solution s, Solution[] solutions) {
        Solution[] tmpSolutions = Arrays.copyOf(solutions, solutions.length + 1);
        tmpSolutions[solutions.length] = s;
        return tmpSolutions;
    }

    public Solution[] findAllByUser(int userId) {
        try (Connection conn = DBUtil.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_BY_USER_ID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                int exerciseId = resultSet.getInt("exercise_id");
                ExerciseDao exerciseDao = new ExerciseDao();
                solution.setExcercise(exerciseDao.read(exerciseId));
                int usersId = resultSet.getInt("users_id");
                UserDao userDao = new UserDao();
                solution.setUser(userDao.read(usersId));
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solution[] findAllByExercise(int exerciseId) {
        try (Connection conn = DBUtil.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_BY_USER_ID);
            statement.setInt(1, exerciseId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                int exerciseId2 = resultSet.getInt("exercise_id");
                ExerciseDao exerciseDao = new ExerciseDao();
                solution.setExcercise(exerciseDao.read(exerciseId2));
                int usersId = resultSet.getInt("users_id");
                UserDao userDao = new UserDao();
                solution.setUser(userDao.read(usersId));
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
