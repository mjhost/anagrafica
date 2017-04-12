package org.mjhost.anagrafica.repository;

import org.junit.After;
import org.junit.Test;
import org.mjhost.anagrafica.model.node.Location;
import org.mjhost.anagrafica.model.node.Organization;
import org.mjhost.anagrafica.model.node.Person;
import org.mjhost.anagrafica.model.relationship.Address;
import org.mjhost.anagrafica.model.relationship.Birth;
import org.mjhost.anagrafica.model.relationship.Death;
import org.mjhost.anagrafica.model.relationship.Wedding;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class WeddingRepositoryTest extends AbstractRepositoryTest {

    protected static final LocalDate WEEDING_DATE = LocalDate.now();

    protected static final String WEEDING_DOCUMENT_RECORD = "a1s2";

//    @Test
//    public void testFindGroomByBrideName() {
//        Person bride = getLady();
//        Person groom = getMan();
//
//        Person p = personRepository.findGroomByBrideName(bride.getFirstName(), bride.getLastName());
//
//        assertThat(p, notNullValue());
//        assertThat(p.getFirstName(), equalTo(groom.getFirstName()));
//        assertThat(p.getLastName(), equalTo(groom.getLastName()));
//        assertThat(p.getSex(), equalTo(groom.getSex()));
//
//        assertThat(p.getWeddings(), notNullValue());
//        assertThat(p.getWeddings().size(), equalTo(1));
//
//        Wedding wedding = p.getWeddings().get(0);
//        assertThat(wedding.getDate(), equalTo(WEEDING_DATE));
//        assertThat(wedding.getDocumentRecord(), equalTo(WEEDING_DOCUMENT_RECORD));
//    }

    @Override
    public void initDatabase() {
//        populate Neo4J

////        nodes
//        Location home = getHomeLocation();
//        getLocationRepository().save(home);
//
//        Person bride = getLady();
//        getPersonRepository().save(bride);
//
//        Person groom = getMan();
//        getPersonRepository().save(groom);
//
//        Organization parish = getParish();
//        getOrganizationRepository().save(parish);
//
////        relationships
//        groom.setBirth(new Birth(groom, home, LocalDateTime.now()));
//        groom.addAddress(new Address(groom, home, LocalDateTime.now(), AddressType.HOME));
//        groom.addWedding(new Wedding(groom, parish, WEEDING_DATE, WEEDING_DOCUMENT_RECORD));
//
//        bride.setBirth(new Birth(bride, home, LocalDateTime.now()));
//        bride.addAddress(new Address(bride, home, LocalDateTime.now(), AddressType.HOME));
//        bride.addWedding(new Wedding(groom, parish, WEEDING_DATE, WEEDING_DOCUMENT_RECORD));
//        bride.setDeath(new Death(bride, home, LocalDateTime.now()));
//
//        personRepository.save(Arrays.asList(groom, bride));
    }

    @After
    public void tearDown() {
//        clean Neo4J
        session.purgeDatabase();
    }
}