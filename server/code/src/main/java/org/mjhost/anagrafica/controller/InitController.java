package org.mjhost.anagrafica.controller;

import org.mjhost.anagrafica.model.enumeration.AddressType;
import org.mjhost.anagrafica.model.node.Location;
import org.mjhost.anagrafica.model.node.Organization;
import org.mjhost.anagrafica.model.node.Person;
import org.mjhost.anagrafica.model.relationship.Address;
import org.mjhost.anagrafica.model.relationship.Birth;
import org.mjhost.anagrafica.model.relationship.Death;
import org.mjhost.anagrafica.model.relationship.Wedding;
import org.mjhost.anagrafica.repository.LocationRepository;
import org.mjhost.anagrafica.repository.OrganizationRepository;
import org.mjhost.anagrafica.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@RestController("/")
public class InitController {

    private LocationRepository locationRepository;

    private OrganizationRepository organizationRepository;

    private PersonRepository personRepository;

    @Autowired
    public InitController(LocationRepository locationRepository, OrganizationRepository organizationRepository, PersonRepository personRepository) {
        this.locationRepository = locationRepository;
        this.organizationRepository = organizationRepository;
        this.personRepository = personRepository;
    }

    @RequestMapping("/init")
    public boolean init() {
//        populate Neo4J

        String ADDRESS = "12, Olympus Street";

        String ZIP_CODE = "11208";

        String CITY = "New York";

        String STATE = "New Jersey";

        String BRIDE_FIRST_NAME = "Anne";

        String BRIDE_LAST_NAME = "Ruiz";

        String FEMALE = "F";

        String GROOM_FIRST_NAME = "Frances";

        String GROOM_LAST_NAME = "Gonzales";

        String MALE = "M";

        String PARISH_NAME = "Olson Church";

        String PARISH_DESCRIPTION = "blah, blah, blah";

        LocalDate WEEDING_DATE = LocalDate.now();

        String WEEDING_DOCUMENT_RECORD = "a1s2";

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
        
        return true;
    }
}
