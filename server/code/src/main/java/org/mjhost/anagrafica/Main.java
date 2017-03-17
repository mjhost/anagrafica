package org.mjhost.anagrafica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan("org.mjhost.anagrafica.model")
@EnableTransactionManagement
@EnableNeo4jRepositories
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);


//        ApplicationContext context = new AnnotationConfigApplicationContext(PersistenceConfiguration.class);

//        PersonRepository personRepository = context.getBean(PersonRepository.class);
//
//        String firstName = "Anne";
//        String lastName = "Ruiz";
//
//        Person person = personRepository.findByName(firstName, lastName);





    }
}