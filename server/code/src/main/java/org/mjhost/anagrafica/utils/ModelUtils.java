package org.mjhost.anagrafica.utils;

import org.mjhost.anagrafica.model.enumeration.ContactType;
import org.mjhost.anagrafica.model.enumeration.EducationLevel;
import org.mjhost.anagrafica.model.node.*;
import org.mjhost.anagrafica.model.relationship.*;

import java.time.LocalDateTime;

public class ModelUtils {

    public static Location getHomeLocation() {
        Location home = new Location();
        home.setAddress("12, Olympus Street");
        home.setZipCode("11208");
        home.setCity("New York");
        home.setState("New Jersey");

        return home;
    }

    public static Contact getHomeContact() {
        Contact home = new Contact();
        home.setPhone("123456");
        home.setEmail("me@myself.org");

        return home;
    }

    public static Job getJob() {
        Job job = new Job();
        job.setName("Software engineer");

        return job;
    }

    public static Subject getPhotographyHobby() {
        Subject hobby = new Subject();
        hobby.setName("Photography");

        return hobby;

    }

    public static Subject getSwimmingHobby() {
        Subject hobby = new Subject();
        hobby.setName("Swimming");

        return hobby;

    }

    public static Person getLady() {
        Location homeLocation = getHomeLocation();
        Contact homeContact = getHomeContact();
        Job job = getJob();
        Subject photography = getPhotographyHobby();
        Subject swimming = getSwimmingHobby();

        Person lady = new Person();
        lady.setFirstName("Anne");
        lady.setLastName("Ruiz");
        lady.setSex("F");
        lady.setTitle("Electrical Engineer");
        lady.setEducationLevel(EducationLevel.BACHELOR_DEGREE);
        lady.setBirth(new Birth(lady, homeLocation, LocalDateTime.now()));
        lady.addAddress(new Address(lady, homeLocation, LocalDateTime.now().minusMonths(6), ContactType.HOME));
        lady.addReference(new Reference(lady, homeContact, ContactType.HOME));
        lady.addEmployment(new Employment(lady, job, LocalDateTime.now().minusYears(10)));
        lady.addHobby(new Hobby(lady, photography, LocalDateTime.now().minusMonths(2)));
        lady.addHobby(new Hobby(lady, swimming, LocalDateTime.now().minusYears(15)));
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
