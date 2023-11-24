package com.dronedelivery.apidrone.repositories;

import com.dronedelivery.apidrone.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepo extends JpaRepository<Entrega, Long>, CustomQuerydslPredicateExecutor<Entrega>{

}
