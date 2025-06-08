package uz.app.db;

import uz.app.entity.Car;
import uz.app.entity.Rental;
import uz.app.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class DB {
    // scanners
    public static final Scanner scannerInt = new Scanner(System.in);
    public static final Scanner scannerStr = new Scanner(System.in);

    // cars
    public static final List<Car> cars = new ArrayList<>();

    // users
    public static final List<User> users = new ArrayList<>();

    // rentals
    public static final List<Rental> rentals = new ArrayList<>();
}
