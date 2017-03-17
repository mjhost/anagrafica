package org.mjhost.anagrafica.persistence.repository;

import org.mjhost.anagrafica.persistence.model.Person;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends GraphRepository<Person> {

//    derived finder
    Person findByFirstName(@Param("firstName") String firstName);

//    @Query("MATCH (m:Movie) WHERE m.title =~ ('(?i).*'+{title}+'.*') RETURN m")
//    Collection<Movie> findByTitleContaining(@Param("title") String title);
//
//    @Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Person) RETURN m,r,a LIMIT {limit}")
//    Collection<Movie> graph(@Param("limit") int limit);
}
