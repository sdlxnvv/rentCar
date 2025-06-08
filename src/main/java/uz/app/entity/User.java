package uz.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class User {
    private UUID id;
    private String name;
    private String password;
    private List<Car> cars = new ArrayList<>();

    public User(UUID id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
