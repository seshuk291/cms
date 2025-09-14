package com.skolli.cms.payment_methods;

import java.util.List;

import com.skolli.cms.transactions.Transactions;
import com.skolli.cms.users.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "payment_methods")
public class PaymentMethods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "payment_type", nullable = false)
    private String paymentType;
    
    @Column(name = "payment_processor", nullable = false)
    private String paymentProcessor;
    
    @ManyToOne
    @JoinColumn(name = "user_id_fk", nullable = false)
    private Users user;

    @OneToMany(mappedBy = "paymentMethod")
    private List<Transactions> transactions;

}
