package com.skolli.cms.order_items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
    List<OrderItems> findByOrderId(Long orderId);
    List<OrderItems> findByProductId(Long productId);
    
    @Query("SELECT oi FROM OrderItems oi WHERE oi.order.id = :orderId")
    List<OrderItems> findAllByOrderId(@Param("orderId") Long orderId);
    
    @Query("SELECT SUM(oi.totalPrice) FROM OrderItems oi WHERE oi.order.id = :orderId")
    Double calculateTotalAmountByOrderId(@Param("orderId") Long orderId);
}