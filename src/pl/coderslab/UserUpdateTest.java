package pl.coderslab;

public class UserUpdateTest {

    public static void main(String[] args) {

        UserDao userDao = new UserDao();
        User user = userDao.read(3);

        System.out.println(user);

        UserGroupDao userGroupDao = new UserGroupDao();
        UserGroup newGroup = userGroupDao.read(3);

        user.setGroup(newGroup);
        userDao.update(user);

        System.out.println(user);
    }
}
