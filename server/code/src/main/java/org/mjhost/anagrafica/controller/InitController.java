package org.mjhost.anagrafica.controller;

import org.mjhost.anagrafica.model.node.Person;
import org.mjhost.anagrafica.model.relationship.Wedding;
import org.mjhost.anagrafica.repository.LocationRepository;
import org.mjhost.anagrafica.repository.OrganizationRepository;
import org.mjhost.anagrafica.repository.PersonRepository;
import org.mjhost.anagrafica.utils.test.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
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
//        delete database
        personRepository.clearDatabase();

//        populate database
        Person ag = AnnalisaGaloppoBuilder.build();
        Person mm = MassimoManfredinoBuilder.build();
        ag.setWedding(new Wedding(ag, mm, LocalDateTime.of(2002, 10, 24, 12, 0, 0)));
        personRepository.save(ag);
//        personRepository.save(mm);

        personRepository.save(GaioGraccoBuilder.build());
        personRepository.save(SempronioGraccoBuilder.build());
        personRepository.save(TiberioGraccoBuilder.build());

        return true;
    }
}
