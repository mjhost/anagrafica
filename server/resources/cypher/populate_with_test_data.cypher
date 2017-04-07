// NODES

// LOCATION

CREATE (
  n:Location {
    address : "12, Olympus Street",
    city : "New York",
    state : "New Jersey",
    zip_code : 11208
  }
)

// ORGANIZATION

CREATE (
  n:Organization {
    name : "Olson Church",
    description : "blah, blah, blah"
  }
)

// PERSON


type=node, props={sex=M, last_name=Gonzales, first_name=Frances}}]}

CREATE (
  n:Person {
    first_name : "Frances",
    last_name : "Gonzales",
    sex : "M"
  }
)





CREATE (
  n:Person {
    first_name : "Anne",
    last_name : "Ruiz",
    sex : "F",
    birth_date : "28/12/1968",
    birth_place : "Porto Ferreira",
    death_date : "28/5/2001",
    death_place : "Gubskaya",
    job_title : "Electrical Engineer",
    education_level : "et magnis dis parturient montes",
    hobbies : "modellismo",
    address_street : "7119 Eagan Junction",
    address_city : "Kuiyong",
    address_country : "Indonesia",
    home_phone : "62-(383)111-2888"
  }
)

// PARISH

CREATE (
  n:Parish {
    name : "Olson Church",
    description : "donec diam",
    address_street : "80 Rigney Lane",
    address_city : "Soio",
    address_country : "Bosnia and Herzegovina",
    home_phone : "387-(918)125-4943"
  }
)

// EDGES

MATCH
  (a:Person),(p:Parish)
WHERE
  a.first_name = "Frances" AND a.last_name = "Gonzales" AND
  p.name = "Olson Church"
CREATE
  (a)-[:GOT_MARRIED_AT{document_record:"a1s2",date:"7/12/2009"}]->(p)

MATCH
  (b:Person),(p:Parish)
WHERE
  b.first_name = "Anne" AND b.last_name = "Ruiz" AND
  p.name = "Olson Church"
CREATE
  (b)-[:GOT_MARRIED_AT{document_record:"a1s2",date:"7/12/2009"}]->(p)
