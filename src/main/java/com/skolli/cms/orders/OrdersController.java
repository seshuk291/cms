package com.skolli.cms.orders;

import com.skolli.cms.orders.dto.CreateOrderDto;
import com.skolli.cms.orders.dto.OrderItemDto;
import com.skolli.cms.orders.dto.UpdateOrderDto;
import com.skolli.cms.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderItemDto>>> getAllOrders() {
        List<OrderItemDto> orders = this.ordersService.findAllOrders();
        return ResponseEntity.ok(ApiResponse.success("Orders retrieved successfully", orders));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderItemDto>> getOrderById(@PathVariable Long orderId) {
        OrderItemDto order = this.ordersService.getOrderById(orderId);
        return ResponseEntity.ok(ApiResponse.success("Order retrieved successfully", order));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<OrderItemDto>>> getOrdersByUserId(@PathVariable Long userId) {
        List<OrderItemDto> orders = this.ordersService.getOrdersByUserId(userId);
        return ResponseEntity.ok(ApiResponse.success("User orders retrieved successfully", orders));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<OrderItemDto>>> getOrdersByStatus(@PathVariable String status) {
        List<OrderItemDto> orders = this.ordersService.getOrdersByStatus(status);
        return ResponseEntity.ok(ApiResponse.success("Orders by status retrieved successfully", orders));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderItemDto>> createOrder(@RequestBody CreateOrderDto orderDto) {
        OrderItemDto createdOrder = this.ordersService.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Order created successfully", createdOrder));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<ApiResponse<Boolean>> updateOrder(
            @PathVariable Long orderId,
            @RequestBody UpdateOrderDto orderDto
    ) {
        Boolean updated = this.ordersService.updateOrder(orderId, orderDto);
        return ResponseEntity.ok(ApiResponse.success("Order updated successfully", updated));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponse<Boolean>> deleteOrder(@PathVariable Long orderId) {
        Boolean deleted = this.ordersService.deleteOrder(orderId);
        return ResponseEntity.ok(ApiResponse.success("Order deleted successfully", deleted));
    }
}