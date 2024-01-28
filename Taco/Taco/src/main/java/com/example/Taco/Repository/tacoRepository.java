package com.example.Taco.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.Taco.taco.Taco;

public interface tacoRepository extends PagingAndSortingRepository <Taco, Long> {
    //pagind and sorting repository is used for pagination and sorting

    @Query("SELECT u FROM Taco u WHERE u.id = :id")
    Optional<Taco>findById(@Param("id") Long id);
}
