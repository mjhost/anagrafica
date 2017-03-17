package org.mjhost.anagrafica;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mjhost.anagrafica.persistence.model.Parish;
import org.mjhost.anagrafica.persistence.model.Person;
import org.mjhost.anagrafica.persistence.model.Wedding;
import org.mjhost.anagrafica.persistence.repository.ParishRepository;
import org.mjhost.anagrafica.persistence.repository.PersonRepository;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { PersistenceConfiguration.class })

@RunWith(SpringRunner.class)
@SpringBootTest
//        (
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
//)
@Import(Neo4jDataAutoConfiguration.class)
@Transactional
public class PersonSpringNeo4JTest {

    @Autowired
    private Session session;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ParishRepository parishRepository;

    @Test
    public void testFindPersonByName() {
        String firstName = "Anne";
        String lastName = "Ruiz";

        Person person = personRepository.findByFirstName(firstName);
        assertThat(person, notNullValue());

//        Person groom = personRepository.findGroomByBrideName(firstName, lastName);
//
//        assertThat(groom, notNullValue());
//        assertThat(person.getSex(), equalTo("F"));
    }

    @Before
    public void setUp() {
//        populate Neo4J
        Person groom = new Person();
        groom.setFirstName("Frances");
        groom.setLastName("Gonzales");
        personRepository.save(groom);

        Person bride = new Person();
        bride.setFirstName("Anne");
        bride.setLastName("Ruiz");
        personRepository.save(bride);

        Parish parish = new Parish();
        parish.setName("Olson Church");
        parish.setDescription("blah, blah, blah");
        parishRepository.save(parish);

        Wedding wedding = new Wedding(groom, parish, "as12");

        parish.addWedding(wedding);
        parishRepository.save(parish);
    }

    @After
    public void tearDown() {
        session.purgeDatabase();
    }
}