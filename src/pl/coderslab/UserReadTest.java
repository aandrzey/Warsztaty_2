package pl.coderslab;

public class UserReadTest {

    public static void main(String[] args) {

        UserDao userDao = new UserDao();
        User user = userDao.read(3);

        System.out.println(user);

        User[] users = userDao.findAll();
        for (User element :
                users) {
            System.out.println(element);
        }
    }
}
