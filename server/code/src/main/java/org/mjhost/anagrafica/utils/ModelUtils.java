package org.mjhost.anagrafica.utils;

import org.mjhost.anagrafica.model.enumeration.ContactType;
import org.mjhost.anagrafica.model.enumeration.EducationLevel;
import org.mjhost.anagrafica.model.node.Contact;
import org.mjhost.anagrafica.model.node.Location;
import org.mjhost.anagrafica.model.node.Person;
import org.mjhost.anagrafica.model.relationship.Address;
import org.mjhost.anagrafica.model.relationship.Birth;
import org.mjhost.anagrafica.model.relationship.Death;
import org.mjhost.anagrafica.model.relationship.Reference;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

public class ModelUtils {

    public static Location getHomeLocation() {
//        TODO: parametrize with properties
        Location home = new Location();
        home.setAddress("12, Olympus Street");
        home.setZipCode("11208");
        home.setCity("New York");
        home.setState("New Jersey");

        return home;
    }

    public static Contact getHomeContact() {
//        TODO: parametrize with properties
        Contact home = new Contact();
        home.setPhone("123456");
        home.setEmail("me@myself.org");

        return home;
    }

    public static Person getLady() {
//        TODO: parametrize with properties
        Location homeLocation = getHomeLocation();
        Contact homeContact = getHomeContact();

        Person lady = new Person();
        lady.setFirstName("Anne");
        lady.setLastName("Ruiz");
        lady.setSex("F");
        lady.setJobTitle("Electrical Engineer");
        lady.setEducationLevel(EducationLevel.BACHELOR_DEGREE);
        lady.setHobbies(new HashSet<>(Arrays.asList(new String[]{"rock music", "fitness"})));
        lady.setBirth(new Birth(lady, homeLocation, LocalDateTime.now()));
        lady.addAddress(new Address(lady, homeLocation, LocalDateTime.now().minusMonths(6), ContactType.HOME));
        lady.addReference(new Reference(lady, homeContact, ContactType.HOME));
        lady.setDeath(new Death(lady, homeLocation, LocalDateTime.now().minusDays(2)));

        return lady;
    }

//    public static Person getMan() {
////        TODO: parametrize with properties
//        Person man = new Person();
//        man.setFirstName("Frances");
//        man.setLastName("Gonzales");
//        man.setSex("M");
//        man.setBirth(new Birth(man, getHomeLocation(), LocalDateTime.now()));
//
//        return man;
//    }

//    public static Organization getParish() {
//        Organization parish = new Organization();
//        parish.setName("Olson Church");
//        parish.setDescription("blah, blah, blah");
//        organizationRepository.save(parish);
//
//        return parish;
//    }
}
