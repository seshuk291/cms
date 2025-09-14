package com.skolli.cms.address;


import com.skolli.cms.users.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "address1", nullable = false)
    private String address1;

    @Column(name = "address2")
    private String address2;
    
    @Column(name = "city", nullable = false)
    private String city;
    
    @Column(name = "state", nullable = false)
    private String state;
    
    @Column(name = "zip", nullable = false)
    private String zipCode;
    
    @Column(name = "country", nullable = false)
    private String country;
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @ManyToOne()
    @JoinColumn(name = "user_id_fk", nullable = false)
    private Users user;
}
