package uz.app.db;

import uz.app.entity.Car;
import uz.app.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DB {
    public static List<User> users = new ArrayList<>();
    public static List<Car> cars = new ArrayList<>();
    public static Scanner scannerInt = new Scanner(System.in);
    public static Scanner scannerStr = new Scanner(System.in);
}
