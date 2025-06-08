package uz.app.service;

import uz.app.db.DB;
import uz.app.entity.User;

public class UserService {
    public void showMenu(User user) {
        boolean isExit = false;

        while (!isExit) {
            System.out.println("""
                    1. show cars
                    2. rent car
                    3. history
                    
                    0. exit
                    """);
            switch (DB.scannerInt.nextInt()) {
                case 1 -> showCars(user);
                case 2 -> rentCar(user);
                case 3 -> showHistory(user);
                case 0 -> isExit = true;
                default -> System.out.println("Invalid input! Try again.");
            }
        }
    }

    private void showCars(User user) {

    }

    private void rentCar(User user) {

    }

    private void showHistory(User user) {

    }
}
