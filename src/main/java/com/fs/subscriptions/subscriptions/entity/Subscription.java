package com.fs.subscriptions.subscriptions.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;
    @Column(name="amount",nullable = false)
    private Double amount;
    private Date duedate;

    @Column(name="customer_id")
    private  String customerId;

    @JsonManagedReference(value="subscription-profiles")
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "subscription",orphanRemoval = true)
    private Set<Profiles> profiles = new HashSet<>();

    public Subscription(Long id,String name,Double amount, Date duedate, String customerId){
        this.id=id;
        this.name=name;
        this.amount=amount;
        this.duedate=duedate;
        this.customerId=customerId;
    }
}
