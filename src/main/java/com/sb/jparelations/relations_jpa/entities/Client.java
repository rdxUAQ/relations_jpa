package com.sb.jparelations.relations_jpa.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

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
    //insert column FK in relation table
    //@JoinColumn(name = "client_id")
    //make a table manually
    @JoinTable(name="tbl_clientes_to_direcciones", 
    joinColumns = @JoinColumn(name="id_cliente"), 
    inverseJoinColumns=@JoinColumn(name="id_direcciones"),
    uniqueConstraints = @UniqueConstraint(columnNames = {"id_direcciones"}))
    private List<Address> addresses;
   
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client", fetch = FetchType.EAGER)
    private List<Invoice> invoices;


    public Client( String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    
    public Client() {
        addresses = new ArrayList<>();
        invoices = new ArrayList<>();
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


    public List<Invoice> getInvoices() {
        return invoices;
    }


    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
    

    @Override
    public String toString() {
        return "Client [id=" + id 
         + ", name=" + name 
         + ", lastName=" + lastName
         + ", addresses=" + addresses 
         + ", invloices="+ invoices 
         + "]";
    }


   


}
