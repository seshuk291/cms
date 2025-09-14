package com.skolli.cms.orders;

import com.skolli.cms.common.custom_exceptions.*;
import com.skolli.cms.orders.dto.CreateOrderDto;
import com.skolli.cms.orders.dto.OrderItemDto;
import com.skolli.cms.orders.dto.UpdateOrderDto;
import com.skolli.cms.transactions.Transactions;
import com.skolli.cms.transactions.TransactionsRepository;
import com.skolli.cms.users.UserRepository;
import com.skolli.cms.users.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersService {
    
    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;
    private final TransactionsRepository transactionsRepository;

    public List<OrderItemDto> findAllOrders() {
        return this.ordersRepository.findAll()
                .stream()
                .map(this::mapOrderToDto)
                .collect(Collectors.toList());
    }

    public OrderItemDto getOrderById(Long orderId) {
        Orders order = this.ordersRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
        return mapOrderToDto(order);
    }

    public List<OrderItemDto> getOrdersByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        return this.ordersRepository.findByUserId(userId)
                .stream()
                .map(this::mapOrderToDto)
                .collect(Collectors.toList());
    }

    public List<OrderItemDto> getOrdersByStatus(String status) {
        return this.ordersRepository.findByStatus(status)
                .stream()
                .map(this::mapOrderToDto)
                .collect(Collectors.toList());
    }

    public OrderItemDto createOrder(CreateOrderDto orderDto) {
        Users user = this.userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + orderDto.getUserId()));
        
        Transactions transaction = this.transactionsRepository.findById(orderDto.getTransactionId())
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id: " + orderDto.getTransactionId()));
        
        try {
            Orders order = Orders.builder()
                    .user(user)
                    .orderDate(orderDto.getOrderDate())
                    .status(orderDto.getStatus())
                    .totalAmount(orderDto.getTotalAmount())
                    .transactions(transaction)
                    .build();
            
            // Note: Address handling would need proper Address repository and validation
            // For now, we'll set the address IDs directly in the entity if needed
            
            Orders savedOrder = this.ordersRepository.save(order);
            return mapOrderToDto(savedOrder);
        } catch (Exception exception) {
            throw new OrderCreationException("Unable to create order: " + exception.getMessage());
        }
    }

    public Boolean updateOrder(Long orderId, UpdateOrderDto orderDto) {
        Orders order = this.ordersRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
        
        try {
            order.setStatus(orderDto.getStatus());
            order.setTotalAmount(orderDto.getTotalAmount());
            // Note: Address updates would need proper Address repository validation
            this.ordersRepository.save(order);
            return true;
        } catch (Exception exception) {
            throw new OrderUpdateException("Unable to update order: " + exception.getMessage());
        }
    }

    public Boolean deleteOrder(Long orderId) {
        if (!this.ordersRepository.existsById(orderId)) {
            throw new OrderNotFoundException("Order not found with id: " + orderId);
        }
        
        try {
            this.ordersRepository.deleteById(orderId);
            return true;
        } catch (Exception exception) {
            throw new OrderDeletionException("Unable to delete order: " + exception.getMessage());
        }
    }

    private OrderItemDto mapOrderToDto(Orders order) {
        return OrderItemDto.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .userName(order.getUser().getUserName())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .billingAddressId(order.getBillingAddress() != null ? order.getBillingAddress().getId() : null)
                .shippingAddressId(order.getShippingAddress() != null ? order.getShippingAddress().getId() : null)
                .transactionId(order.getTransactions() != null ? order.getTransactions().getId() : null)
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}