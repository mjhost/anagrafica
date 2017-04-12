package org.mjhost.anagrafica.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mjhost.anagrafica.ContactManager;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
}
