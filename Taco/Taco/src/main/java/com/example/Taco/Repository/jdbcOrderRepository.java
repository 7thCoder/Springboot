package com.example.Taco.Repository;

import java.util.Date;
import java.util.List;
import java.sql.Types;
import java.util.Arrays;

import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Taco.Repository.orderRepository;
import com.example.Taco.taco.Taco;
import com.example.Taco.taco.Ingredient;
import com.example.Taco.taco.tacOrder;
import com.example.Taco.ingredientRef;

@Repository
public class jdbcOrderRepository implements orderRepository {
    private JdbcOperations jdbcOperations;

    public jdbcOrderRepository(JdbcOperations jdbcOperations){
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public tacOrder save(tacOrder order){
        PreparedStatementCreatorFactory pscf = 
            new PreparedStatementCreatorFactory(
                "insert into Taco-Order"
                + "(delivery_name, delivery_street, delivery_city,"
                + "delivery_state, delivery_zip, cc_number,"
                + "cc_expiration, cc_cvv, placed_at)"
                + "vaues (?,?,?,?,?,?,?,?,?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
            );
            pscf.setReturnGeneratedKeys(true);

            order.setPlacedAt(new Date ());
            PreparedStatementCreator psc = 
                pscf.newPreparedStatementCreator(
                    Arrays .asList(
                        order.getDeliveryName(),
                        order.getDeliveryStreet(),
                        order.getDeliveryCity(),
                        order.getDeliveryState(),
                        order.getDeliveryZip(),
                        order.getCcNumber(),
                        order.getCcExpiration(),
                        order.getCcCVV(),
                        order.getPlacedAt()
                    )
                );

            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcOperations.update(psc, keyHolder);
            long orderId = keyHolder.getKey().longValue();
            order.setId(orderId);

            List<Taco> tacos = order.getTacos ();
            int i = 0;
            for(Taco taco : tacos){
                saveTaco(orderId, i++, taco);
            }
        return order;
    }
    public long saveTaco(Long orderId, int orderKey,Taco taco){
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf =
            new PreparedStatementCreatorFactory(
                "insert into Taco"
                + "(name, created_at, taco_order, taco-order_key)"
                + "values (?, ?, ?, ?)",
                Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
            );

        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = 
            pscf.newPreparedStatementCreator(Arrays.asList(
                taco.getName(),
                taco.getCreatedAt(),
                orderId,
                orderKey
            ));
        
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);

        saveIngredientRefs(tacoId, taco.getIngredients());

        return tacoId;
    }
    private void saveIngredientRefs(
        long tacoId, List<ingredientRef> ingredientRefs){
            int key = 0;
            for (ingredientRef ingredientRef : ingredientRefs ){
                jdbcOperations.update(
                    "insert into ingredient, taco, taco_key)"
                    + "values (?, ?, ?)",
                    ingredientRef.getIngredient(), tacoId, key++);
            }
        }
}