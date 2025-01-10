package com.sb.jparelations.relations_jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street_avenue;
    private String colony;
    private Integer number;



    

    public Address() {
    }

    public Address(String street_avenue, String colony, Integer number) {
        this.street_avenue = street_avenue;
        this.colony = colony;
        this.number = number;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet_avenue() {
        return street_avenue;
    }

    public void setStreet_avenue(String street_avenue) {
        this.street_avenue = street_avenue;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address [id=" + id + ", street_avenue=" + street_avenue + ", colony=" + colony + ", number=" + number
                + "]";
    }

    

    
    
    

}
