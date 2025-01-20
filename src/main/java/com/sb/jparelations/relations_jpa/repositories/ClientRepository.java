package com.sb.jparelations.relations_jpa.repositories;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sb.jparelations.relations_jpa.entities.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    
    @Query("select c from Client c join fetch c.addresses")
    Optional<Client> findOne(Long id);



}
