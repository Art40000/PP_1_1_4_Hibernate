package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        for (int i = 1; i <= 4; i++) {
            userService.saveUser("Name" + i, "Lastname" + i, (byte) (i + 30));
            System.out.println(userService.getAllUsers());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
