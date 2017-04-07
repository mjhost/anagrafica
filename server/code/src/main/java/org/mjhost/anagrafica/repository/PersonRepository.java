package org.mjhost.anagrafica.repository;

import org.mjhost.anagrafica.model.node.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface PersonRepository extends GraphRepository<Person> {

//    derived finders
    List<Person> findByFirstName(@NotNull @Param("firstName") String firstName);

    List<Person> findByLastName(@NotNull @Param("lastName") String lastName);
//    derived finders

    @Query(
        " MATCH " +
            " (p:Person) " +
        " WHERE " +
            " p.first_name = {fn} AND p.last_name = {ln} " +
        " RETURN " +
            " p "
    )
    List<Person> findByName(@NotNull @Param("fn") String firstName, @NotNull @Param("ln") String lastName);

    @Query(
        " MATCH " +
            " (g:Person)-[mg:GOT_MARRIED_AT]->(p:Organization)<-[mb:GOT_MARRIED_AT]-(b:Person) " +
        " WHERE " +
            " b.first_name = {fn} AND b.last_name = {ln} AND mg.document_record = mb.document_record " +
        " RETURN " +
            " g AS groom, mg as wedding"
    )
    Person findGroomByBrideName(@NotNull @Param("fn") String firstName, @NotNull @Param("ln") String lastName);



//    @Query("MATCH (m:Movie) WHERE m.title =~ ('(?i).*'+{title}+'.*') RETURN m")
//    Collection<Movie> findByTitleContaining(@Param("title") String title);
//
//    @Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Person) RETURN m,r,a LIMIT {limit}")
//    Collection<Movie> graph(@Param("limit") int limit);



}
