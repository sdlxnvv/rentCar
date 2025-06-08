package uz.app.service;

import uz.app.db.DB;
import uz.app.entity.User;

import java.util.UUID;

public class AuthService {
    private final UserService userService = new UserService();
    private final AdminService adminService = new AdminService();

    public void signIn() {
        System.out.print("Please enter your name: ");
        String name = DB.scannerStr.nextLine();

        System.out.print("Please enter your password: ");
        String password = DB.scannerStr.nextLine();

        DB.users.stream()
                .filter(u -> u.getName().equals(name) && u.getPassword().equals(password))
                .findFirst()
                .ifPresentOrElse(user -> {
                    if (user.getName().equals("admin")) {
                        adminService.showMenu();
                    } else {
                        userService.showMenu(user);
                    }
                }, () -> System.out.println("❌User not found!"));

    }

    public void signUp() {
        System.out.print("Please enter your name: ");
        String name = DB.scannerStr.nextLine();

        boolean userExists = DB.users.stream()
                .anyMatch(u -> u.getName().equalsIgnoreCase(name));

        if (userExists) {
            System.out.println("❌User already exists!");
            return;
        }

        System.out.print("Please enter your password: ");
        String password = DB.scannerStr.nextLine();

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setId(UUID.randomUUID());

        DB.users.add(user);
    }
}