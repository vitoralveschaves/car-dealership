package br.com.api.dealership.queryfilters;

import br.com.api.dealership.entities.Car;
import br.com.api.dealership.enums.CarModel;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

import static br.com.api.dealership.specifications.CarSpecification.*;

@Data
public class CarQueryFilter {
    private CarModel model;
    private Boolean used;
    private BigDecimal price;
    private Integer year;
    private String make;
    private Integer mileage;

    public Specification<Car> toSpecification() {
        return findByModel(model)
                .and(findByUsed(used))
                .and(priceLessThanOrEquals(price))
                .and(yearLessThanOrEquals(year))
                .and(findByMake(make))
                .and(mileageLessOrEquals(mileage));
    }
}
