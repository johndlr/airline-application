package com.juandlr.customer.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "customers")
@Getter @Setter @ToString @NoArgsConstructor
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name = "mobile_number", updatable = false)
    private String mobileNumber;

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    public Customer(String name, String lastName, String email, String mobileNumber, Address address) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }

}
