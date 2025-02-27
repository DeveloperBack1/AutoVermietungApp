package com.schneider.spring.springboot.autovermietungapp.repository;

import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.entity.enums.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for {@link Car} entity that provides CRUD operations and custom queries.
 * <p>
 * This interface extends {@link JpaRepository} to provide standard CRUD functionality
 * and includes custom methods for querying {@link Car} entities based on their brand, model, and ID.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    /**
     * Finds a list of {@link Car} entities by their brand.
     *
     * @param brandUppercase the {@link Brand} enum value used for the query.
     * @return a list of {@link Car} entities with the specified brand.
     */
    List<Car> findByBrand(Brand brandUppercase);

    /**
     * Finds a list of {@link Car} entities by their model.
     *
     * @param model the model of the car used for the query.
     * @return a list of {@link Car} entities with the specified model.
     */
    List<Car> findCarsByModel(String model);

    /**
     * Finds an optional {@link Car} entity by its ID.
     *
     * @param id the ID of the car to be queried.
     * @return an {@link Optional} containing the found {@link Car} or empty if not found.
     */
    Optional<Car> findCarById(Integer id);
}