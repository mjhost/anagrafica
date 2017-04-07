package org.mjhost.anagrafica.repository;

import org.mjhost.anagrafica.model.node.Organization;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends GraphRepository<Organization> {}
