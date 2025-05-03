package com.example.demo.repository.specification;

import com.example.demo.dto.FlightFilterDTO;
import com.example.demo.entity.Flight;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class FlightSpecifications {

    public static Specification<Flight> withFilter(FlightFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Фильтрация по departure
            if (filter.getDeparture() != null && !filter.getDeparture().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("departure")),
                        "%" + filter.getDeparture().toLowerCase() + "%"
                ));
            }

            // Добавьте другие условия фильтрации (если есть)
            if (filter.getDestination() != null && !filter.getDestination().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("destination")),
                        "%" + filter.getDestination().toLowerCase() + "%"
                ));
            }

            if (filter.getAirplaneId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("airplaneId"), filter.getAirplaneId()));
            }

            if (filter.getFlightNumber() != null && !filter.getFlightNumber().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("flightNumber")),
                        "%" + filter.getFlightNumber().toLowerCase() + "%"
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}