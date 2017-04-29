package org.mjhost.anagrafica.utils.test;

import org.mjhost.anagrafica.model.enumeration.ContactType;
import org.mjhost.anagrafica.model.enumeration.EducationLevel;
import org.mjhost.anagrafica.model.node.*;
import org.mjhost.anagrafica.model.relationship.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CarmelaManfredinoBuilder {

    private static Location getBirthLocation() {
        Location birth = new Location();
        birth.setName("Ospedale Scafati");
        birth.setStreet("Via Passanti, 1");
        birth.setZipCode("84018");
        birth.setCity("Scafati");
        birth.setState("Campania");
        birth.setProvince("SA");
        birth.setCountry("Italia");
        birth.setLat(40.755064);
        birth.setLon(14.525998);

        return birth;
    }

    private static Location getHomeLocation() {
        Location home = new Location();
        home.setStreet("Via Genova, 34");
        home.setZipCode("84018");
        home.setCity("Scafati");
        home.setState("Campania");
        home.setProvince("SA");
        home.setCountry("Italia");
        home.setLat(40.755078);
        home.setLon(14.530947);

        return home;
    }

    private static Contact getHomeContact() {
        Contact home = new Contact();
        home.setPhone("0811234567");

        return home;
    }

    private static Set<Job> getJobs() {
        Job job = new Job();
        job.setName("Maestra elementare");

        return new HashSet<>(Arrays.asList(job));
    }

    private static Set<Subject> getHobbies() {
        Subject caccia = new Subject();
        caccia.setName("Cucina");

        return new HashSet<>(Arrays.asList(caccia));
    }

    public static Person build() {
        Person p = new Person();
        p.setFirstName("Carmela");
        p.setLastName("Manfredino");
        p.setSex("F");
        p.setTitle("Mrs");
        p.setEducationLevel(EducationLevel.HIGH_SCHOOL);
        p.setBirth(new Birth(p, getBirthLocation(), LocalDateTime.of(1938, 9, 7, 0, 0)));
        p.addAddress(new Address(p, getHomeLocation(), LocalDateTime.now().minusYears(50), ContactType.HOME));
        p.addReference(new Reference(p, getHomeContact(), ContactType.HOME));
        getJobs().stream().forEach(j -> p.addEmployment(new Employment(p, j, LocalDateTime.now().minusYears(18))));
        getHobbies().stream().forEach(h -> p.addHobby(new Hobby(p, h, LocalDateTime.now().minusMonths(60))));
        return p;
    }
}
