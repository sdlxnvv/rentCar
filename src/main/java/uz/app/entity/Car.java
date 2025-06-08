package uz.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.app.enums.CarColor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private UUID id;
    private String name;
    private CarColor color;
    private Long priceForDay;
}
