package com.fs.subscriptions.subscriptions.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profiles {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="name",nullable = false)
    private String name;

    @ManyToOne
    @JsonBackReference(value = "subscription-profiles")
    private Subscription subscription;

    public Profiles(Long id,String name){
        this.id=id;
        this.name=name;
    }

}

