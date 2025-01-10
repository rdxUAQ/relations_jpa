package com.sb.jparelations.relations_jpa.entities;

import java.util.List;

import jakarta.persistence.Entity;
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

    @OneToMany
    private List<Invoice> invoices;
   
    
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
    


    @Override
    public String toString() {
        return "Client {id=" + id + ", name=" + name + ", lastName=" + lastName + "}";
    }


    public List<Invoice> getInvoices() {
        return invoices;
    }


    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }


    
    

}
