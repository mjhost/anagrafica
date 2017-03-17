package org.mjhost.anagrafica.repository;

import org.mjhost.anagrafica.model.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource(collectionResourceRel = "movies", path = "movies")
@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    Person findByName(@Param("firstName") String firstName, @Param("lastName") String lastName);

//    @Query("MATCH (m:Movie) WHERE m.title =~ ('(?i).*'+{title}+'.*') RETURN m")
//    Collection<Movie> findByTitleContaining(@Param("title") String title);
//
//    @Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Person) RETURN m,r,a LIMIT {limit}")
//    Collection<Movie> graph(@Param("limit") int limit);
}
