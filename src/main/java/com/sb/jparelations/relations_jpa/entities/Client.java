package com.sb.jparelations.relations_jpa.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, 
        orphanRemoval = true,
        fetch = FetchType.EAGER)
    private List<Address> addresses;
   
    public Client( String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    
    public Client() {
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    public List<Address> getAddresses() {
        return addresses;
    }


    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }


    @Override
    public String toString() {
        return "Client [id=" + id + ", name=" + name + ", lastName=" + lastName + ", addresses=" + addresses + "]";
    }


}
