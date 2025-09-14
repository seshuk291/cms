package com.skolli.cms.order_items;

import com.skolli.cms.order_items.dto.CreateOrderItemsDto;
import com.skolli.cms.order_items.dto.OrderItemsItemDto;
import com.skolli.cms.order_items.dto.UpdateOrderItemsDto;
import com.skolli.cms.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
@RequiredArgsConstructor
public class OrderItemsController {

    private final OrderItemsService orderItemsService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderItemsItemDto>>> getAllOrderItems() {
        List<OrderItemsItemDto> orderItems = this.orderItemsService.findAllOrderItems();
        return ResponseEntity.ok(ApiResponse.success("Order items retrieved successfully", orderItems));
    }

    @GetMapping("/{orderItemId}")
    public ResponseEntity<ApiResponse<OrderItemsItemDto>> getOrderItemById(@PathVariable Long orderItemId) {
        OrderItemsItemDto orderItem = this.orderItemsService.getOrderItemById(orderItemId);
        return ResponseEntity.ok(ApiResponse.success("Order item retrieved successfully", orderItem));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse<List<OrderItemsItemDto>>> getOrderItemsByOrderId(@PathVariable Long orderId) {
        List<OrderItemsItemDto> orderItems = this.orderItemsService.getOrderItemsByOrderId(orderId);
        return ResponseEntity.ok(ApiResponse.success("Order items by order retrieved successfully", orderItems));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse<List<OrderItemsItemDto>>> getOrderItemsByProductId(@PathVariable Long productId) {
        List<OrderItemsItemDto> orderItems = this.orderItemsService.getOrderItemsByProductId(productId);
        return ResponseEntity.ok(ApiResponse.success("Order items by product retrieved successfully", orderItems));
    }

    @GetMapping("/order/{orderId}/total")
    public ResponseEntity<ApiResponse<Double>> calculateTotalAmountByOrderId(@PathVariable Long orderId) {
        Double totalAmount = this.orderItemsService.calculateTotalAmountByOrderId(orderId);
        return ResponseEntity.ok(ApiResponse.success("Order total calculated successfully", totalAmount));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderItemsItemDto>> createOrderItem(@RequestBody CreateOrderItemsDto orderItemDto) {
        OrderItemsItemDto createdOrderItem = this.orderItemsService.createOrderItem(orderItemDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Order item created successfully", createdOrderItem));
    }

    @PutMapping("/{orderItemId}")
    public ResponseEntity<ApiResponse<Boolean>> updateOrderItem(
            @PathVariable Long orderItemId,
            @RequestBody UpdateOrderItemsDto orderItemDto
    ) {
        Boolean updated = this.orderItemsService.updateOrderItem(orderItemId, orderItemDto);
        return ResponseEntity.ok(ApiResponse.success("Order item updated successfully", updated));
    }

    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<ApiResponse<Boolean>> deleteOrderItem(@PathVariable Long orderItemId) {
        Boolean deleted = this.orderItemsService.deleteOrderItem(orderItemId);
        return ResponseEntity.ok(ApiResponse.success("Order item deleted successfully", deleted));
    }
}