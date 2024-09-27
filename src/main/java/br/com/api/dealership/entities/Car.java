package br.com.api.dealership.entities;

import br.com.api.dealership.enums.CarModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tb_car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "car_id")
    private UUID id;
    private String name;
    private Integer year;
    private BigDecimal price;
    private Boolean used;
    private Integer mileage;
    @Enumerated(EnumType.STRING)
    private CarModel model;

    @ManyToOne(cascade = CascadeType.ALL)
    private Make make;

    public Car(String name,
               Integer year,
               BigDecimal price,
               Boolean used,
               Integer mileage,
               CarModel model,
               Make make) {
        this.name = name;
        this.year = year;
        this.price = price;
        this.used = used;
        this.mileage = mileage;
        this.model = model;
        this.make = make;
    }

    public Car(String name,
               Integer year,
               BigDecimal price,
               Boolean used,
               Integer mileage,
               CarModel model) {
        this.name = name;
        this.year = year;
        this.price = price;
        this.mileage = mileage;
        this.used = used;
        this.model = model;
    }
}
