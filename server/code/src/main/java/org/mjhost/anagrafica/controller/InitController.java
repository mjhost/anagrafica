package org.mjhost.anagrafica.controller;

import org.mjhost.anagrafica.repository.LocationRepository;
import org.mjhost.anagrafica.repository.OrganizationRepository;
import org.mjhost.anagrafica.repository.PersonRepository;
import org.mjhost.anagrafica.utils.test.GaioGraccoBuilder;
import org.mjhost.anagrafica.utils.test.MassimoManfredinoBuilder;
import org.mjhost.anagrafica.utils.test.SempronioGraccoBuilder;
import org.mjhost.anagrafica.utils.test.TiberioGraccoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        personRepository.save(MassimoManfredinoBuilder.build());
        personRepository.save(GaioGraccoBuilder.build());
        personRepository.save(SempronioGraccoBuilder.build());
        personRepository.save(TiberioGraccoBuilder.build());

        return true;
    }
}
