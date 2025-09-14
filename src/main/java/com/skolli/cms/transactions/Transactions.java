package com.skolli.cms.transactions;

import java.math.BigDecimal;

import com.skolli.cms.orders.Orders;
import com.skolli.cms.payment_methods.PaymentMethods;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @ManyToOne()
    @JoinColumn(name = "payment_method_id_fk", nullable = false)
    private PaymentMethods paymentMethod;

    @OneToOne(mappedBy = "transactions")
    private Orders order;
}
