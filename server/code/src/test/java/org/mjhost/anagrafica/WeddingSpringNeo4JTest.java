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

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
//        (
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
//)
@Import(Neo4jDataAutoConfiguration.class)
@Transactional
public class WeddingSpringNeo4JTest {

    protected final static String BRIDE_FIRST_NAME = "Anne";

    protected final static String BRIDE_LAST_NAME = "Ruiz";

    protected static final String FEMALE = "F";

    protected final static String GROOM_FIRST_NAME = "Frances";

    protected final static String GROOM_LAST_NAME = "Gonzales";

    protected static final String MALE = "M";

    protected static final String PARISH_NAME = "Olson Church";

    protected static final String PARISH_DESCRIPTION = "blah, blah, blah";

    protected static final Date WEEDING_DATE = Calendar.getInstance().getTime();

    protected static final String WEEDING_DOCUMENT_RECORD = "a1s2";

    @Autowired
    protected Session session;

    @Autowired
    protected PersonRepository personRepository;

    @Autowired
    protected ParishRepository parishRepository;

    @Test
    public void testFindBrideByFirstName() {
        Person bride = personRepository.findByFirstName(BRIDE_FIRST_NAME);
        assertThat(bride, notNullValue());
        assertThat(bride.getLastName(), equalTo(BRIDE_LAST_NAME));
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
        Person groom = new Person();
        groom.setFirstName(GROOM_FIRST_NAME);
        groom.setLastName(GROOM_LAST_NAME);
        groom.setSex(MALE);
        personRepository.save(groom);

        Person bride = new Person();
        bride.setFirstName(BRIDE_FIRST_NAME);
        bride.setLastName(BRIDE_LAST_NAME);
        bride.setSex(FEMALE);
        personRepository.save(bride);

        Parish parish = new Parish();
        parish.setName(PARISH_NAME);
        parish.setDescription(PARISH_DESCRIPTION);
        parish.addWedding(new Wedding(bride, parish, WEEDING_DATE, WEEDING_DOCUMENT_RECORD));
        parish.addWedding(new Wedding(groom, parish, WEEDING_DATE, WEEDING_DOCUMENT_RECORD));
        parishRepository.save(parish);
    }

    @After
    public void tearDown() {
//        clean Neo4J
        session.purgeDatabase();
    }
}