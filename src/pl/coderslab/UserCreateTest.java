package pl.coderslab;

public class UserCreateTest {

    public static void main(String[] args) {

        //Stworzenie nowego obiektu klasy User
        User user = new User();
        user.setUsername("Marek");
        user.setEmail("marek@gmail.com");
        user.setPassword("pass1");

        //Stworzenie Grupy userów
        UserGroupDao userGroupDao = new UserGroupDao();
        UserGroup userGroup = userGroupDao.read(2);

        user.setGroup(userGroup);

        //Stworzenie nowego obieku klasy UserDao
        UserDao userDao =  new UserDao();
        //Stworzenie Usera w tabeli
        userDao.create(user);


    }
}
