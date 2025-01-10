package com.sb.jparelations.relations_jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sb.jparelations.relations_jpa.entities.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    @Query("SELECT c.name, i.id FROM Client c JOIN c.invoices i WHERE i.client.id = c.id")
    public List<Object[]> getManyToOne();



}
