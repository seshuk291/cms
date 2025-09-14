package com.skolli.cms.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUserId(Long userId);
    List<Orders> findByStatus(String status);
    
    @Query("SELECT o FROM Orders o WHERE o.user.id = :userId AND o.status = :status")
    List<Orders> findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") String status);
    
    @Query("SELECT o FROM Orders o WHERE o.user.id = :userId ORDER BY o.orderDate DESC")
    List<Orders> findByUserIdOrderByOrderDateDesc(@Param("userId") Long userId);
}