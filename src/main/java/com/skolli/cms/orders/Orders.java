package com.skolli.cms.orders;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.skolli.cms.address.Address;
import com.skolli.cms.transactions.Transactions;
import com.skolli.cms.users.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id_fk", nullable = false)
    Users user;

    @Column(name = "order_date", nullable = false)
    Timestamp orderDate;

    @Column(name = "status", nullable = false)
    String status;

    @Column(name = "total_amount", nullable = false)
    BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "billing_address_id_fk", nullable = false)
    private Address billingAddress;

    @ManyToOne
    @JoinColumn(name = "shipping_address_id_fk", nullable = false)
    private Address shippingAddress;

    @OneToOne
    @JoinColumn(name = "transaction_id_fk", nullable = false)
    private Transactions transactions;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    Timestamp updatedAt;

}
