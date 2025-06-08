package uz.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class User {
    private UUID id;
    private String name;
    private String password;

    public User(UUID id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
