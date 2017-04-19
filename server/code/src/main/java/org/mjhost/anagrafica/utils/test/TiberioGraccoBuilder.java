package org.mjhost.anagrafica.utils.test;

import org.mjhost.anagrafica.model.enumeration.ContactType;
import org.mjhost.anagrafica.model.enumeration.EducationLevel;
import org.mjhost.anagrafica.model.node.*;
import org.mjhost.anagrafica.model.relationship.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TiberioGraccoBuilder {

    private static Location getBirthLocation() {
        Location birth = new Location();

        birth.setStreet("Via Colle S. Bartolomeo, 50");
        birth.setZipCode("80045");
        birth.setCity("Pompei");
        birth.setState("Campania");
        birth.setProvince("NA");
        birth.setCountry("Italia");
        birth.setLat(40.747867);
        birth.setLon(14.494867);

        return birth;
    }

    private static Location getHomeLocation() {
        Location home = new Location();
        home.setStreet("Via Peveragno, 3");
        home.setZipCode("00166");
        home.setCity("Roma");
        home.setState("Lazio");
        home.setProvince("RM");
        home.setCountry("Italia");
        home.setLat(41.902362);
        home.setLon(12.415397);

        return home;
    }

    private static Contact getHomeContact() {
        Contact home = new Contact();
        home.setPhone("06123456");
        home.setEmail("massimo.manfredino@gmail.com");

        return home;
    }

    private static Set<Job> getJobs() {
        Job job = new Job();
        job.setName("Software engineer");

        return new HashSet<>(Arrays.asList(job));
    }

    private static Set<Subject> getHobbies() {
        Subject p = new Subject();
        p.setName("Fotografia");

        Subject s = new Subject();
        s.setName("Nuoto");

        return new HashSet<>(Arrays.asList(p, s));
    }

    public static Person build() {
        Person p = new Person();
        p.setId(9999L);
        p.setFirstName("Tiberio");
        p.setLastName("Gracco");
        p.setSex("M");
        p.setTitle("Mr");
        p.setEducationLevel(EducationLevel.PHD);
        p.setBirth(new Birth(p, getBirthLocation(), LocalDateTime.now().minusYears(12)));
        p.addAddress(new Address(p, getHomeLocation(), LocalDateTime.now().minusYears(3), ContactType.HOME));
        p.addReference(new Reference(p, getHomeContact(), ContactType.HOME));
        getJobs().stream().forEach(j -> p.addEmployment(new Employment(p, j, LocalDateTime.now().minusYears(18))));
        getHobbies().stream().forEach(h -> p.addHobby(new Hobby(p, h, LocalDateTime.now().minusMonths(2))));

        return p;
    }
}
