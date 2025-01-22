package com.sb.jparelations.relations_jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sb.jparelations.relations_jpa.entities.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    
    @Query("select c from Client c left join fetch c.addresses where c.id=?1")
    Optional<Client> findOne(Long id);

    @Query("SELECT c from Client c left join fetch c.invoices where c.id=?1")
    Optional<Client> findOneWithInvoices(Long id);



}
