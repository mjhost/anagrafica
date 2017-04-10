package org.mjhost.anagrafica.graph;

import org.junit.Test;
import org.mjhost.anagrafica.model.node.Person;
import org.mjhost.anagrafica.model.relationship.Wedding;
import org.mjhost.anagrafica.repository.WeddingRepositoryTest;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class WeddingGraphTest extends WeddingRepositoryTest {

    @Test
    public void testFindGroomByBrideName() {
        Person bride = getLady();
        Person groom = personRepository.findGroomByBrideName(bride.getFirstName(), bride.getLastName());

        assertThat(groom, notNullValue());
        assertThat(groom.getFirstName(), equalTo(groom.getFirstName()));
        assertThat(groom.getLastName(), equalTo(groom.getLastName()));
        assertThat(groom.getSex(), equalTo(groom.getSex()));

        assertThat(groom.getWeddings(), notNullValue());
        assertThat(groom.getWeddings().size(), equalTo(1));

        Wedding wedding = groom.getWeddings().get(0);
        assertThat(wedding.getDate(), equalTo(WEEDING_DATE));
        assertThat(wedding.getDocumentRecord(), equalTo(WEEDING_DOCUMENT_RECORD));
    }
}