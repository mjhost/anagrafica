package org.mjhost.anagrafica.repository;

import org.mjhost.anagrafica.model.Parish;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParishRepository extends GraphRepository<Parish> {


}
