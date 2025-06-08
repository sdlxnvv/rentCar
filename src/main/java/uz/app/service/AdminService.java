package uz.app.service;

import uz.app.db.DB;
import uz.app.entity.Car;
import uz.app.entity.Rental;
import uz.app.enums.CarColor;

import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AdminService {
    public void showMenu() {
        boolean isExit = false;

        while (!isExit) {
            System.out.print("""
                    1. add car
                    2. show cars
                    3. manage car
                    4. show report
                    
                    0. exit
                    =>\s""");

            switch (DB.scannerInt.nextInt()) {
                case 1 -> addCar();
                case 2 -> showCars();
                case 3 -> manageCar();
                case 4 -> showReport();
                case 0 -> isExit = true;
                default -> System.out.println("Invalid input! Try again.");
            }
        }
    }

    // main methods
    private void addCar() {
        System.out.print("Enter car name: ");
        String carName = DB.scannerStr.nextLine();

        CarColor selectedColor = chooseColor();

        long priceForDay;
        do {
            System.out.print("Enter price for a day: ");
            priceForDay = DB.scannerInt.nextLong();
            if (priceForDay <= 0) System.out.println("❌ Price must be positive.");
        } while (priceForDay <= 0);

        Car car = new Car(UUID.randomUUID(), carName, selectedColor, priceForDay);
        boolean carExists = DB.cars.stream()
                .anyMatch(u -> u.getName().equals(car.getName()));

        if (!carExists) {
            System.out.println("✅Car added!");
            DB.cars.add(car);
        } else {
            System.out.println("❌Car already exists!");
        }
    }

    private void showCars() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        DB.cars.forEach(System.out::println);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private void manageCar() {
        boolean isExit = false;
        if (!DB.cars.isEmpty()) {
            while (!isExit) {
                System.out.print("""
                        1. edit name
                        2. edit price
                        3. edit color
                        4. delete car
                        
                        0. exit
                        =>\s""");
                switch (DB.scannerInt.nextInt()) {
                    case 1 -> editCarName();
                    case 2 -> editCarPrice();
                    case 3 -> editCarColor();
                    case 4 -> deleteCar();
                    case 0 -> isExit = true;
                    default -> System.out.println("Invalid input! Try again.");
                }
            }
        } else {
            System.out.println("❌No cars in DB");
        }
    }

    private void showReport() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        if (DB.rentals.isEmpty()) {
            System.out.println("❌ Пока нет аренд.");
            return;
        }

        Map<Car, Long> totalRentalCars = DB.rentals.stream()
                .collect(Collectors.groupingBy(
                        Rental::getCar,
                        Collectors.counting()
                ));

        System.out.println("📊 Count of rent: ");
        totalRentalCars.forEach((car, count) -> {
            System.out.println("🚗 " + car.getName() + " — " + count);
        });
        System.out.println();
        long totalSum = DB.rentals.stream()
                .mapToLong(r -> {
                    long days = ChronoUnit.DAYS.between(r.getStartDate(), r.getEndDate()) + 1;
                    return r.getCar().getPriceForDay() * days;
                })
                .sum();

        System.out.println("💰Total sum: " + totalSum);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }


    // edit methods
    private void editCarName() {
        findCarByName().ifPresentOrElse(
                u -> {
                    System.out.print("Enter the new name: ");
                    u.setName(DB.scannerStr.nextLine());
                    System.out.println("✅Successfully edited!\nNew name: " + u.getName());
                },
                () -> System.out.println("Name not found!")
        );
    }

    private void editCarPrice() {
        findCarByName().ifPresentOrElse(
                u -> {
                    long price;
                    do {
                        System.out.print("Enter price for a day: ");
                        price = DB.scannerInt.nextLong();
                        if (price <= 0) System.out.println("❌ Price must be positive.");
                    } while (price <= 0);
                    u.setPriceForDay(price);
                    System.out.println("✅Successfully edited!\nNew price: " + u.getPriceForDay());

                },
                () -> System.out.println("Invalid input! Try again."));
    }

    private void editCarColor() {
        findCarByName().ifPresentOrElse(
                u -> {
                    CarColor carColor = chooseColor();
                    u.setColor(carColor);
                    System.out.println("✅Successfully edited!\nNew color: " + u.getColor());
                },
                () -> System.out.println("Invalid input! Try again.")
        );
    }

    private void deleteCar() {
        findCarByName().ifPresentOrElse(
                u -> {
                    DB.cars.remove(u);
                    System.out.println("✅Successfully deleted!");
                },
                () -> System.out.println("❌ Car not found!")
        );
    }

    private Optional<Car> findCarByName() {
        showCars();
        System.out.print("Enter car name: ");
        String name = DB.scannerStr.nextLine();
        return DB.cars.stream().filter(u -> u.getName().equals(name)).findFirst();
    }


    // duplicate methods
    private CarColor chooseColor() {
        CarColor[] colors = CarColor.values();
        while (true) {
            IntStream.range(0, colors.length)
                    .forEach(i -> System.out.println((i + 1) + ". " + colors[i]));
            System.out.print("Choose number of color: ");
            int choice = DB.scannerInt.nextInt();
            if (choice >= 1 && choice <= colors.length) {
                return colors[choice - 1];
            }
            System.out.println("❌ Invalid input. Try again.");
        }
    }
}