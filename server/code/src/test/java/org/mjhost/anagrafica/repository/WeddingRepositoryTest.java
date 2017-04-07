package org.mjhost.anagrafica.repository;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mjhost.anagrafica.ContactManager;
import org.mjhost.anagrafica.model.enumeration.AddressType;
import org.mjhost.anagrafica.model.node.Location;
import org.mjhost.anagrafica.model.node.Organization;
import org.mjhost.anagrafica.model.node.Person;
import org.mjhost.anagrafica.model.relationship.Address;
import org.mjhost.anagrafica.model.relationship.Birth;
import org.mjhost.anagrafica.model.relationship.Death;
import org.mjhost.anagrafica.model.relationship.Wedding;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ContextConfiguration(classes = {ContactManager.class})
public class WeddingRepositoryTest {

    protected static final String ADDRESS = "12, Olympus Street";

    protected static final String ZIP_CODE = "11208";

    protected static final String CITY = "New York";

    protected static final String STATE = "New Jersey";

    protected final static String BRIDE_FIRST_NAME = "Anne";

    protected final static String BRIDE_LAST_NAME = "Ruiz";

    protected static final String FEMALE = "F";

    protected final static String GROOM_FIRST_NAME = "Frances";

    protected final static String GROOM_LAST_NAME = "Gonzales";

    protected static final String MALE = "M";

    protected static final String PARISH_NAME = "Olson Church";

    protected static final String PARISH_DESCRIPTION = "blah, blah, blah";

    protected static final LocalDate WEEDING_DATE = LocalDate.now();

    protected static final String WEEDING_DOCUMENT_RECORD = "a1s2";

    @Autowired
    protected Session session;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    protected OrganizationRepository organizationRepository;

    @Autowired
    protected PersonRepository personRepository;

    @Test
    public void testFindBrideByFirstName() {
        List<Person> people = personRepository.findByFirstName(BRIDE_FIRST_NAME);

        assertThat(CollectionUtils.isEmpty(people), is(false));
        assertThat(people.size(), is(1));

        Person bride = people.get(0);
        assertThat(bride, notNullValue());
        assertThat(bride.getLastName(), equalTo(BRIDE_LAST_NAME));
        assertThat(bride.getSex(), equalTo(FEMALE));
    }

    @Test
    public void testFindBrideByName() {
        List<Person> people = personRepository.findByName(BRIDE_FIRST_NAME, BRIDE_LAST_NAME);
        assertThat(CollectionUtils.isEmpty(people), is(false));
        assertThat(people.size(), is(1));
        Person bride = people.get(0);
        assertThat(bride, notNullValue());
        assertThat(bride.getSex(), equalTo(FEMALE));
    }

    @Test
    public void testFindGroomByBrideName() {
        Person groom = personRepository.findGroomByBrideName(BRIDE_FIRST_NAME, BRIDE_LAST_NAME);

        assertThat(groom, notNullValue());
        assertThat(groom.getFirstName(), equalTo(GROOM_FIRST_NAME));
        assertThat(groom.getLastName(), equalTo(GROOM_LAST_NAME));
        assertThat(groom.getSex(), equalTo(MALE));

        assertThat(groom.getWeddings(), notNullValue());
        assertThat(groom.getWeddings().size(), equalTo(1));

        Wedding wedding = groom.getWeddings().get(0);
        assertThat(wedding.getDate(), equalTo(WEEDING_DATE));
        assertThat(wedding.getDocumentRecord(), equalTo(WEEDING_DOCUMENT_RECORD));
    }

    @Before
    public void setUp() {
//        populate Neo4J

//        nodes
        Location home = new Location();
        home.setAddress(ADDRESS);
        home.setZipCode(ZIP_CODE);
        home.setCity(CITY);
        home.setState(STATE);
        locationRepository.save(home);

        Organization parish = new Organization();
        parish.setName(PARISH_NAME);
        parish.setDescription(PARISH_DESCRIPTION);
        organizationRepository.save(parish);

        Person groom = new Person();
        groom.setFirstName(GROOM_FIRST_NAME);
        groom.setLastName(GROOM_LAST_NAME);
        groom.setSex(MALE);

        Person bride = new Person();
        bride.setFirstName(BRIDE_FIRST_NAME);
        bride.setLastName(BRIDE_LAST_NAME);
        bride.setSex(FEMALE);

//        relationships
        groom.setBirth(new Birth(groom, home, LocalDateTime.now()));
        groom.addAddress(new Address(groom, home, LocalDateTime.now(), AddressType.HOME));
        groom.addWedding(new Wedding(groom, parish, WEEDING_DATE, WEEDING_DOCUMENT_RECORD));

        bride.setBirth(new Birth(bride, home, LocalDateTime.now()));
        bride.addAddress(new Address(bride, home, LocalDateTime.now(), AddressType.HOME));
        bride.addWedding(new Wedding(groom, parish, WEEDING_DATE, WEEDING_DOCUMENT_RECORD));
        bride.setDeath(new Death(bride, home, LocalDateTime.now()));

        personRepository.save(Arrays.asList(groom, bride));
    }

    @After
    public void tearDown() {
//        clean Neo4J
        session.purgeDatabase();
    }
}