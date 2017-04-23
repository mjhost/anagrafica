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

    List<Person> findBySex(@NotNull @Param("sex") String sex);
//    derived finders

    @Query(
        " MATCH " +
            " (p:Person) " +
        " WHERE " +
            " p.first_name = {fn} AND p.last_name = {ln} " +
        " RETURN " +
            " p "
    )
    List<Person> findByFullName(@NotNull @Param("fn") String firstName, @NotNull @Param("ln") String lastName);

    @Query(
        " MATCH " +
            " (p:Person)-[r]->(t) " +
        " WHERE " +
            " ID(p) = {id} " +
        " RETURN p, r, t "
    )
    Person findById(@NotNull @Param("id") Long id);

    @Query(
        " MATCH " +
            " (p:Person) " +
        " WHERE " +
            " TOLOWER(p.first_name + \" \" + p.last_name) CONTAINS(TOLOWER({fn})) OR " +
            " TOLOWER(p.last_name + \" \" + p.first_name) CONTAINS(TOLOWER({fn})) " +
        " WITH p MATCH " +
            " (p)-[r]->(t) " +
        " RETURN p, r, t"
    )
    List<Person> findByName(@NotNull @Param("fn") String name);

    @Query(
        " MATCH " +
            " (p:Person)-[h:HAS_HOBBY]->(s:Subject) " +
        " WHERE " +
            " TOLOWER(s.name) CONTAINS TOLOWER({ho}) " +
        " WITH p MATCH " +
            " (p)-[r]->(t) " +
        " RETURN p, r, t"
    )
    List<Person> findByHobby(@NotNull @Param("ho") String hobby);

    @Query(
        " MATCH " +
            " (p:Person)-[e:IS_EMPLOYED_AS]->(j:Job) " +
        " WHERE " +
            " TOLOWER(j.name) CONTAINS TOLOWER({em}) " +
        " WITH p MATCH " +
            " (p)-[r]->(t) " +
        " RETURN p, r, t"
    )
    List<Person> findByEmployment(@NotNull @Param("em") String employment);








//    WARN: THIS IS JUST FOR TESTING PURPOSES AND MUST BE DELETED
    @Query("MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r")
    void clearDatabase();
//    WARN: THIS IS JUST FOR TESTING PURPOSES AND MUST BE DELETED




//    @Query(
//        " MATCH " +
//            " (g:Person)-[mg:GOT_MARRIED_AT]->(p:Organization)<-[mb:GOT_MARRIED_AT]-(b:Person) " +
//        " WHERE " +
//            " b.first_name = {fn} AND b.last_name = {ln} AND mg.document_record = mb.document_record " +
//        " RETURN " +
//            " g AS groom, mg as wedding"
//    )
//    Person findGroomByBrideName(@NotNull @Param("fn") String firstName, @NotNull @Param("ln") String lastName);



//    @Query("MATCH (m:Movie) WHERE m.title =~ ('(?i).*'+{title}+'.*') RETURN m")
//    Collection<Movie> findByTitleContaining(@Param("title") String title);
//
//    @Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Person) RETURN m,r,a LIMIT {limit}")
//    Collection<Movie> graph(@Param("limit") int limit);



}
