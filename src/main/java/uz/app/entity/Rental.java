package uz.app.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rental {
    private UUID id;
    private Car car;
    private User user;
    private LocalDate startDate;
    private LocalDate endDate;
}