package org.mjhost.anagrafica.persistence.repository;

import org.mjhost.anagrafica.persistence.model.Parish;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParishRepository extends GraphRepository<Parish> {


}
