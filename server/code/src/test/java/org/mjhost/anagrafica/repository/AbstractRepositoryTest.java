package org.mjhost.anagrafica.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mjhost.anagrafica.ContactManager;
import org.mjhost.anagrafica.model.node.Location;
import org.mjhost.anagrafica.model.node.Organization;
import org.mjhost.anagrafica.model.node.Person;
import org.mjhost.anagrafica.model.relationship.Birth;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ContextConfiguration(classes = {ContactManager.class})
public abstract class AbstractRepositoryTest {

    @Autowired
    protected Session session;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    protected OrganizationRepository organizationRepository;

    @Autowired
    protected PersonRepository personRepository;

    public Session getSession() {
        return session;
    }

    public LocationRepository getLocationRepository() {
        return locationRepository;
    }

    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    @Before
    public void setUp() {
//        populate Neo4J
        initDatabase();
    }

    @After
    public void tearDown() {
//        clean Neo4J
        session.purgeDatabase();
    }

    protected abstract void initDatabase();

    protected Location getHomeLocation() {
//        TODO: parametrize with properties
        Location home = new Location();
        home.setAddress("12, Olympus Street");
        home.setZipCode("11208");
        home.setCity("New York");
        home.setState("New Jersey");

        return home;
    }

    protected Person getLady() {
//        TODO: parametrize with properties
        Person lady = new Person();
        lady.setFirstName("Anne");
        lady.setLastName("Ruiz");
        lady.setSex("F");
        lady.setBirth(new Birth(lady, getHomeLocation(), LocalDateTime.now()));

        return lady;
    }

    protected Person getMan() {
//        TODO: parametrize with properties
        Person man = new Person();
        man.setFirstName("Frances");
        man.setLastName("Gonzales");
        man.setSex("M");
        man.setBirth(new Birth(man, getHomeLocation(), LocalDateTime.now()));

        return man;
    }

    protected Organization getParish() {
        Organization parish = new Organization();
        parish.setName("Olson Church");
        parish.setDescription("blah, blah, blah");
        organizationRepository.save(parish);

        return parish;
    }
}
