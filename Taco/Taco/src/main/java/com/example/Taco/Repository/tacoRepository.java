package com.example.Taco.Repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.example.Taco.taco.Taco;

public interface tacoRepository extends PagingAndSortingRepository <Taco, Long>, CrudRepository <Taco, Long> {
    //pagind and sorting repository is used for pagination and sorting


}
