package com.crm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// https://www.baeldung.com/jpa-one-to-one
@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "city", nullable = false)
    private String city;

    @OneToOne
    @MapsId
    @JoinColumn(name="user_id")
    private User user;

}