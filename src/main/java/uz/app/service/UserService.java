package uz.app.service;

import uz.app.db.DB;
import uz.app.entity.Car;
import uz.app.entity.Rental;
import uz.app.entity.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.UUID;

public class UserService {
    public void showMenu(User user) {
        boolean isExit = false;

        while (!isExit) {
            System.out.print("""
                    1. show cars
                    2. rent car
                    3. history
                    
                    0. exit
                    =>\s""");
            switch (DB.scannerInt.nextInt()) {
                case 1 -> showCars();
                case 2 -> rentCar(user);
                case 3 -> showHistory(user);
                case 0 -> isExit = true;
                default -> System.out.println("Invalid input! Try again.");
            }
        }
    }

    private void showCars() {
        if (!DB.cars.isEmpty()) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            DB.cars.forEach(System.out::println);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        } else {
            System.out.println("❌No cars found.");
        }
    }

    private void rentCar(User user) {
        findCarByName().ifPresentOrElse(
                car -> {
                    LocalDate start = inputDate("Enter start date ");
                    LocalDate end = inputDate("Enter end date ");

                    if (start.isBefore(end)) {
                        boolean carAvailable = isCarAvailable(car, start, end);
                        if (carAvailable) {
                            DB.rentals.add(new Rental(UUID.randomUUID(), car, user, start, end));
                            System.out.println("✅Successfully rented!");
                        } else {
                            System.out.println("❌Car is not available.");
                        }
                    } else {
                        System.out.println("❌Start date must be before end date!");
                    }
                },
                () -> System.out.println("❌Car not found!")
        );
    }

    private void showHistory(User user) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        DB.rentals.stream()
                .filter(r -> r.getUser().equals(user))
                .forEach(System.out::println);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    // necessaries methods
    private Optional<Car> findCarByName() {
        showCars();
        System.out.print("Enter car name: ");
        String name = DB.scannerStr.nextLine();
        return DB.cars.stream().filter(u -> u.getName().equals(name)).findFirst();
    }

    private LocalDate inputDate(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = null;

        while (date == null) {
            System.out.print(prompt + " (formate: dd-MM-yyyy): ");
            String input = DB.scannerStr.nextLine();

            try {
                date = LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("❌ Wrong input! Try again.");
            }
        }

        return date;
    }

    boolean isCarAvailable(Car car, LocalDate newFrom, LocalDate newTo) {
        return DB.rentals.stream()
                .filter(r -> r.getCar().equals(car))
                .allMatch(rent ->
                        newTo.isBefore(rent.getStartDate()) || newFrom.isAfter(rent.getEndDate())
                );
    }

}
