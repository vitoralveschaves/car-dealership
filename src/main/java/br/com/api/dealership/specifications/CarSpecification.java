package br.com.api.dealership.specifications;

import br.com.api.dealership.entities.Car;
import br.com.api.dealership.entities.Make;
import br.com.api.dealership.enums.CarModel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;

public class CarSpecification {

    public static Specification<Car> findByModel(CarModel model) {
        return ((root, query, criteriaBuilder) -> {
            if(ObjectUtils.isEmpty(model)) {
                return null;
            }
            return criteriaBuilder.equal(root.get("model"), model);
        });
    }

    public static Specification<Car> findByUsed(Boolean used) {
        return ((root, query, criteriaBuilder) -> {
            if(ObjectUtils.isEmpty(used)) {
                return null;
            }
            return criteriaBuilder.equal(root.get("used"), used);
        });
    }

    public static Specification<Car> priceLessThanOrEquals(BigDecimal price) {
        return ((root, query, criteriaBuilder) -> {
            if(ObjectUtils.isEmpty(price)) {
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
        });
    }

    public static Specification<Car> yearLessThanOrEquals(Integer year) {
        return ((root, query, criteriaBuilder) -> {
            if(ObjectUtils.isEmpty(year)) {
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("year"), year);
        });
    }

    public static Specification<Car> findByMake(String make) {
        return ((root, query, criteriaBuilder) -> {
            if(ObjectUtils.isEmpty(make)) {
                return null;
            }
            return root.join("make").get("name").in(make);
        });
    }

    public static Specification<Car> mileageLessOrEquals(Integer mileage) {
        return ((root, query, criteriaBuilder) -> {
            if(ObjectUtils.isEmpty(mileage)) {
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("mileage"), mileage);
        });
    }

}
