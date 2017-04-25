package org.mjhost.anagrafica.utils.test;

import org.mjhost.anagrafica.model.enumeration.ContactType;
import org.mjhost.anagrafica.model.enumeration.EducationLevel;
import org.mjhost.anagrafica.model.node.*;
import org.mjhost.anagrafica.model.relationship.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FrancescoManfredinoBuilder {

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
        home.setStreet("Via Leonardo da Vinci, 7");
        home.setZipCode("84018");
        home.setCity("Scafati");
        home.setState("Campania");
        home.setProvince("SA");
        home.setCountry("Italia");
        home.setLat(40.749678);
        home.setLon(14.522367);

        return home;
    }

    private static Contact getHomeContact() {
        Contact home = new Contact();
        home.setPhone("0811234567");

        return home;
    }

    private static Set<Job> getJobs() {
        Job job = new Job();
        job.setName("Dirigente scolastico");

        return new HashSet<>(Arrays.asList(job));
    }

    private static Set<Subject> getHobbies() {
        Subject caccia = new Subject();
        caccia.setName("Caccia");

        return new HashSet<>(Arrays.asList(caccia));
    }

    public static Person build() {
        Person p = new Person();
        p.setFirstName("Francesco");
        p.setLastName("Manfredino");
        p.setSex("M");
        p.setTitle("Mr");
        p.setEducationLevel(EducationLevel.MASTER_DEGREE);
        p.setBirth(new Birth(p, getBirthLocation(), LocalDateTime.of(1942, 9, 7, 0, 0)));
        p.addAddress(new Address(p, getHomeLocation(), LocalDateTime.now().minusYears(50), ContactType.HOME));
        p.addReference(new Reference(p, getHomeContact(), ContactType.HOME));
        getJobs().stream().forEach(j -> p.addEmployment(new Employment(p, j, LocalDateTime.now().minusYears(18))));
        getHobbies().stream().forEach(h -> p.addHobby(new Hobby(p, h, LocalDateTime.now().minusMonths(60))));
        p.setDeath(new Death(p, getHomeLocation(), LocalDateTime.of(2012, 4, 12, 0, 0, 0)));
        return p;
    }
}
