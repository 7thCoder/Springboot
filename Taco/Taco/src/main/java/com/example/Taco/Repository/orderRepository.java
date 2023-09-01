package com.example.Taco.Repository;
import org.springframework.stereotype.Repository;

import com.example.Taco.taco.tacOrder;

@Repository
public interface orderRepository {
    
    tacOrder save(tacOrder order);
}
