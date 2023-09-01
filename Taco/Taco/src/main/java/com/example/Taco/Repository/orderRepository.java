package com.example.Taco.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Taco.taco.tacOrder;

public interface orderRepository extends CrudRepository<tacOrder, Long> {
    
    tacOrder save(tacOrder order);
}
