package uz.app.db;

import uz.app.entity.Car;
import uz.app.entity.Rental;
import uz.app.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DB {
    // scanners
    public static final Scanner scannerInt = new Scanner(System.in);
    public static final Scanner scannerStr = new Scanner(System.in);

    // data
    public static final List<Car> cars = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    public static final List<Rental> rentals = new ArrayList<>();

}
