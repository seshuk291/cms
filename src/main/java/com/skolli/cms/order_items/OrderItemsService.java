package com.skolli.cms.order_items;

import com.skolli.cms.common.custom_exceptions.*;
import com.skolli.cms.order_items.dto.CreateOrderItemsDto;
import com.skolli.cms.order_items.dto.OrderItemsItemDto;
import com.skolli.cms.order_items.dto.UpdateOrderItemsDto;
import com.skolli.cms.orders.Orders;
import com.skolli.cms.orders.OrdersRepository;
import com.skolli.cms.products.Products;
import com.skolli.cms.products.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemsService {
    
    private final OrderItemsRepository orderItemsRepository;
    private final OrdersRepository ordersRepository;
    private final ProductsRepository productsRepository;

    public List<OrderItemsItemDto> findAllOrderItems() {
        return this.orderItemsRepository.findAll()
                .stream()
                .map(this::mapOrderItemToDto)
                .collect(Collectors.toList());
    }

    public OrderItemsItemDto getOrderItemById(Long orderItemId) {
        OrderItems orderItem = this.orderItemsRepository.findById(orderItemId)
                .orElseThrow(() -> new OrderItemNotFoundException("Order item not found with id: " + orderItemId));
        return mapOrderItemToDto(orderItem);
    }

    public List<OrderItemsItemDto> getOrderItemsByOrderId(Long orderId) {
        if (!ordersRepository.existsById(orderId)) {
            throw new OrderNotFoundException("Order not found with id: " + orderId);
        }
        return this.orderItemsRepository.findByOrderId(orderId)
                .stream()
                .map(this::mapOrderItemToDto)
                .collect(Collectors.toList());
    }

    public List<OrderItemsItemDto> getOrderItemsByProductId(Long productId) {
        if (!productsRepository.existsById(productId)) {
            throw new ProductNotFoundException("Product not found with id: " + productId);
        }
        return this.orderItemsRepository.findByProductId(productId)
                .stream()
                .map(this::mapOrderItemToDto)
                .collect(Collectors.toList());
    }

    public OrderItemsItemDto createOrderItem(CreateOrderItemsDto orderItemDto) {
        Orders order = this.ordersRepository.findById(orderItemDto.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderItemDto.getOrderId()));
        
        Products product = this.productsRepository.findById(orderItemDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + orderItemDto.getProductId()));
        
        try {
            OrderItems orderItem = OrderItems.builder()
                    .quantity(orderItemDto.getQuantity())
                    .unitPrice(orderItemDto.getUnitPrice())
                    .totalPrice(orderItemDto.getQuantity() * orderItemDto.getUnitPrice())
                    .product(product)
                    .order(order)
                    .build();
            
            OrderItems savedOrderItem = this.orderItemsRepository.save(orderItem);
            return mapOrderItemToDto(savedOrderItem);
        } catch (Exception exception) {
            throw new OrderItemCreationException("Unable to create order item: " + exception.getMessage());
        }
    }

    public Boolean updateOrderItem(Long orderItemId, UpdateOrderItemsDto orderItemDto) {
        OrderItems orderItem = this.orderItemsRepository.findById(orderItemId)
                .orElseThrow(() -> new OrderItemNotFoundException("Order item not found with id: " + orderItemId));
        
        try {
            orderItem.setQuantity(orderItemDto.getQuantity());
            orderItem.setUnitPrice(orderItemDto.getUnitPrice());
            orderItem.setTotalPrice(orderItemDto.getQuantity() * orderItemDto.getUnitPrice());
            this.orderItemsRepository.save(orderItem);
            return true;
        } catch (Exception exception) {
            throw new OrderItemUpdateException("Unable to update order item: " + exception.getMessage());
        }
    }

    public Boolean deleteOrderItem(Long orderItemId) {
        if (!this.orderItemsRepository.existsById(orderItemId)) {
            throw new OrderItemNotFoundException("Order item not found with id: " + orderItemId);
        }
        
        try {
            this.orderItemsRepository.deleteById(orderItemId);
            return !this.orderItemsRepository.existsById(orderItemId);
        } catch (Exception exception) {
            throw new OrderItemDeletionException("Unable to delete order item: " + exception.getMessage());
        }
    }

    public Double calculateTotalAmountByOrderId(Long orderId) {
        if (!ordersRepository.existsById(orderId)) {
            throw new OrderNotFoundException("Order not found with id: " + orderId);
        }
        return this.orderItemsRepository.calculateTotalAmountByOrderId(orderId);
    }

    private OrderItemsItemDto mapOrderItemToDto(OrderItems orderItem) {
        return OrderItemsItemDto.builder()
                .id(orderItem.getId())
                .quantity(orderItem.getQuantity())
                .unitPrice(orderItem.getUnitPrice())
                .totalPrice(orderItem.getTotalPrice())
                .productId(orderItem.getProduct().getId())
                .productName(orderItem.getProduct().getName())
                .orderId(orderItem.getOrder().getId())
                .createdAt(orderItem.getCreatedAt())
                .updatedAt(orderItem.getUpdatedAt())
                .build();
    }
}