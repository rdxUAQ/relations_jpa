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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Address other = (Address) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Address [id=" + id + ", street_avenue=" + street_avenue + ", colony=" + colony + ", number=" + number
                + "]";
    }

    

    
    
    

}
