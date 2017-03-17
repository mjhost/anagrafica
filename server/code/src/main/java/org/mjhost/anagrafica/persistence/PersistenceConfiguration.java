package org.mjhost.anagrafica.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"org.mjhost.anagrafica"})
@EnableNeo4jRepositories(basePackages = { "org.mjhost.anagrafica.persistence.repository" } )
@EntityScan( basePackages = { "org.mjhost.anagrafica.persistence.model" } )
public class PersistenceConfiguration {

//    @Bean
//    public SessionFactory getSessionFactory() {
//        return new SessionFactory("org.mjhost.anagrafica.model");
//    }
}
