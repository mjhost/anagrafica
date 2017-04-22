package org.mjhost.anagrafica.repository;

import org.mjhost.anagrafica.model.relationship.Wedding;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeddingRepository extends GraphRepository<Wedding> {

    @Query(
        " MATCH " +
            " (h:Person)-[wed:IS_MARRIED_TO]-(w:Person) " +
        " WHERE " +
            " h.sex = \"M\" AND w.sex = \"F\" " +
        " RETURN " +
            " h, wed, w"
    )
    List<Wedding> findAll();
}
