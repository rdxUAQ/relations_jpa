package com.sb.jparelations.relations_jpa.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sb.jparelations.relations_jpa.entities.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {




}
