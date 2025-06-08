package uz.app;

import uz.app.db.DB;
import uz.app.entity.User;
import uz.app.service.AuthService;

import java.util.UUID;

public class Main {
    private static final AuthService authService = new AuthService();
    public static void main(String[] args) {
        DB.users.add(new User(UUID.randomUUID(), "admin", "admin"));
        boolean isExit = false;

        while (!isExit) {
            System.out.print("""
                    1. sign in
                    2. sign up
                    
                    0. exit
                    =>\s""");
            switch (DB.scannerInt.nextInt()) {
                case 1 -> authService.signIn();
                case 2 -> authService.signUp();
                case 0 -> isExit = true;
                default -> System.out.println("Invalid input. Try again.");
            }
        }
    }
}