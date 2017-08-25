BEGIN
// FAMIGLIE
CREATE (:Person {first_name: "John", last_name: "Doe", sex:"M", uuid: "1"})
CREATE (:Person {first_name: "Jane", last_name: "Doe", sex:"F", uuid: "2"})

CREATE (:Person {first_name: "Father", last_name: "Doe", sex:"M", uuid: "3"})
CREATE (:Person {first_name: "Mother", last_name: "Roe", sex:"F", uuid: "4"})

CREATE (:Person {first_name: "GrandFather", last_name: "Doe", sex:"M", uuid: "5"})
CREATE (:Person {first_name: "GrandMother", last_name: "Doe", sex:"F", uuid: "6"})
CREATE (:Person {first_name: "GrandFather", last_name: "Roe", sex:"M", uuid: "7"})
CREATE (:Person {first_name: "GrandMother", last_name: "Roe", sex:"F", uuid: "8"})

CREATE (:Person:Dead {first_name: "Father-GrandFather", last_name: "Doe", sex:"M", dead_on: 841795200000, uuid: "21"}) //1996-09-04
CREATE (:Person:Dead {first_name: "Mother-GrandFather", last_name: "Doe", sex:"F", dead_on: 1210896000000, uuid: "23"}) //2008-05-16
CREATE (:Person:Dead {first_name: "Father-GrandMother", last_name: "Doe", sex:"M", dead_on: 1049414400000, uuid: "22"}) //2003-04-04
CREATE (:Person:Dead {first_name: "Mother-GrandMother", last_name: "Doe", sex:"F", dead_on: 948240000000, uuid: "24"}) //2000-01-19

CREATE (:Person:Dead {first_name: "Father-GrandFather", last_name: "Roe", sex:"M", dead_on: 1038528000000, uuid: "25"}) //2002-11-29
CREATE (:Person:Dead {first_name: "Mother-GrandFather", last_name: "Roe", sex:"F", dead_on: 1243296000000, uuid: "27"}) //2009-05-26
CREATE (:Person:Dead {first_name: "Father-GrandMother", last_name: "Roe", sex:"M", dead_on: 751593600000, uuid: "26"}) //1993-10-26
CREATE (:Person:Dead {first_name: "Mother-GrandMother", last_name: "Roe", sex:"F", dead_on: 1245974400000, uuid: "28"}) //2009-06-26

CREATE (:Person {first_name: "Hugh", last_name: "Parson", sex:"M", uuid: "14"})

// FAMIGLIE PER DASHBOARD
CREATE (:Person:Dead {first_name: "Tom", last_name: "Nobodies", sex:"M", dead_on: 977616000000, uuid: "39"}) //2000-12-24
CREATE (:Person:Dead {first_name: "Dick", last_name: "Nobodies", sex:"M", dead_on: 1274572800000, uuid: "40"}) //2010-05-23
CREATE (:Person {first_name: "Harriet", last_name: "Nobodies", sex:"F", uuid: "41"})
CREATE (:Person {first_name: "Pinco", last_name: "Pallino", sex:"M", uuid: "42"})


// POSTI
CREATE (:Church {name: "Parrocchia di N. S. di Guadalupe e S. Filippo Martire", uuid: "9"})
CREATE (:Location { address: "Via Aurelia, 675", zip_code: "00165", city: "Roma", province:"RM", state:"Lazio", country: "Italy", lat: 41.894162, lng:12.416332, uuid: "10" })

CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:"Alaska", country: "USA", lat: 63.55176, lng:-149.40731, uuid: "11" })
CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:null, country: "Red Sea", lat: 22.03725, lng:38.53758, uuid: "12" })
CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:null, country: "Indian Ocean", lat: -17.41584, lng:89.83238, uuid: "13" })
CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:null, country: null, lat: null, lng:null, uuid: "15" })
CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:null, country: null, lat: null, lng:null, uuid: "16" })
CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:null, country: null, lat: null, lng:null, uuid: "17" })
CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:null, country: null, lat: null, lng:null, uuid: "18" })
CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:null, country: null, lat: null, lng:null, uuid: "19" })
CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:null, country: null, lat: null, lng:null, uuid: "20" })
CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:null, country: null, lat: null, lng:null, uuid: "29" })
CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:null, country: null, lat: null, lng:null, uuid: "31" })

// POSTI LIBERI
CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:null, country: null, lat: null, lng:null, uuid: "30" })
CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:null, country: null, lat: null, lng:null, uuid: "32" })
CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:null, country: null, lat: null, lng:null, uuid: "33" })
CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:null, country: null, lat: null, lng:null, uuid: "34" })
CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:null, country: null, lat: null, lng:null, uuid: "35" })
CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:null, country: null, lat: null, lng:null, uuid: "36" })
CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:null, country: null, lat: null, lng:null, uuid: "37" })
CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:null, country: null, lat: null, lng:null, uuid: "38" })
CREATE (:Location { address: null, zip_code: null, city: null, province:null, state:null, country: null, lat: null, lng:null, uuid: "39" })

// EVENTI
// should weddings be a node? relationships to church and priest might be simpler but maybe those are useless

COMMIT
BEGIN
// PARENTELE
MATCH (n1:Person{uuid:"1"}), (n2:Person{uuid:"2"}) CREATE (n1)-[r:`IS_SIBLING_OF`]->(n2);
MATCH (n1:Person{uuid:"3"}), (n2:Person{uuid:"1"}) CREATE (n1)-[r:`IS_PARENT_OF`]->(n2);
MATCH (n1:Person{uuid:"4"}), (n2:Person{uuid:"1"}) CREATE (n1)-[r:`IS_PARENT_OF`]->(n2);
MATCH (n1:Person{uuid:"3"}), (n2:Person{uuid:"2"}) CREATE (n1)-[r:`IS_PARENT_OF`]->(n2);
MATCH (n1:Person{uuid:"4"}), (n2:Person{uuid:"2"}) CREATE (n1)-[r:`IS_PARENT_OF`]->(n2);
MATCH (n1:Person{uuid:"5"}), (n2:Person{uuid:"3"}) CREATE (n1)-[r:`IS_PARENT_OF`]->(n2);
MATCH (n1:Person{uuid:"6"}), (n2:Person{uuid:"3"}) CREATE (n1)-[r:`IS_PARENT_OF`]->(n2);
MATCH (n1:Person{uuid:"7"}), (n2:Person{uuid:"4"}) CREATE (n1)-[r:`IS_PARENT_OF`]->(n2);
MATCH (n1:Person{uuid:"8"}), (n2:Person{uuid:"4"}) CREATE (n1)-[r:`IS_PARENT_OF`]->(n2);

MATCH (n1:Person{uuid:"21"}), (n2:Person{uuid:"5"}) CREATE (n1)-[r:`IS_PARENT_OF`]->(n2);
MATCH (n1:Person{uuid:"23"}), (n2:Person{uuid:"5"}) CREATE (n1)-[r:`IS_PARENT_OF`]->(n2);
MATCH (n1:Person{uuid:"22"}), (n2:Person{uuid:"6"}) CREATE (n1)-[r:`IS_PARENT_OF`]->(n2);
MATCH (n1:Person{uuid:"24"}), (n2:Person{uuid:"6"}) CREATE (n1)-[r:`IS_PARENT_OF`]->(n2);
MATCH (n1:Person{uuid:"25"}), (n2:Person{uuid:"7"}) CREATE (n1)-[r:`IS_PARENT_OF`]->(n2);
MATCH (n1:Person{uuid:"27"}), (n2:Person{uuid:"7"}) CREATE (n1)-[r:`IS_PARENT_OF`]->(n2);
MATCH (n1:Person{uuid:"26"}), (n2:Person{uuid:"8"}) CREATE (n1)-[r:`IS_PARENT_OF`]->(n2);
MATCH (n1:Person{uuid:"28"}), (n2:Person{uuid:"8"}) CREATE (n1)-[r:`IS_PARENT_OF`]->(n2);

// MATRIMONI
MATCH (n1:Person{uuid:"3"}), (n2:Person{uuid:"4"}) CREATE (n1)-[r:`IS_MARRIED_TO` {date: 1423872000000}]->(n2); //2015-02-14
MATCH (n1:Person{uuid:"3"}), (n2:Church{uuid:"9"}) CREATE (n1)-[r:`DID_MARRY_IN` {date: 1423872000000}]->(n2); //2015-02-14
MATCH (n1:Person{uuid:"4"}), (n2:Church{uuid:"9"}) CREATE (n1)-[r:`DID_MARRY_IN` {date: 1423872000000}]->(n2); //2015-02-14
MATCH (n1:Person{uuid:"14"}), (n2:Person{uuid:"3"}) CREATE (n1)-[r:`DID_OFFICIATE_WEDDING` {date: 1423872000000}]->(n2); //2015-02-14
MATCH (n1:Person{uuid:"14"}), (n2:Person{uuid:"4"}) CREATE (n1)-[r:`DID_OFFICIATE_WEDDING` {date: 1423872000000}]->(n2); //2015-02-14
// INDIRIZZI
MATCH (n1:Church{uuid:"9"}), (n2:Location{uuid:"10"}) CREATE (n1)-[r:`HAS_ADDRESS`]->(n2);
MATCH (n1:Person{uuid:"3"}), (n2:Location{uuid:"11"}) CREATE (n1)-[r:`HAS_ADDRESS` {date: 1029974400000}]->(n2); //2002-08-22
MATCH (n1:Person{uuid:"4"}), (n2:Location{uuid:"11"}) CREATE (n1)-[r:`HAS_ADDRESS` {date: 1274054400000}]->(n2); //2010-05-17
MATCH (n1:Person{uuid:"1"}), (n2:Location{uuid:"11"}) CREATE (n1)-[r:`HAS_ADDRESS` {date: 1306108800000}]->(n2); //2011-05-23
MATCH (n1:Person{uuid:"2"}), (n2:Location{uuid:"11"}) CREATE (n1)-[r:`HAS_ADDRESS` {date: 1377734400000}]->(n2); //2013-08-29
// NASCITE
MATCH (n1:Person{uuid:"1"}), (n2:Location{uuid:"12"}) CREATE (n1)-[r:`BORN` {date: 1306108800000}]->(n2); //2011-05-23
MATCH (n1:Person{uuid:"2"}), (n2:Location{uuid:"13"}) CREATE (n1)-[r:`BORN` {date: 1377734400000}]->(n2); //2013-08-29
MATCH (n1:Person{uuid:"3"}), (n2:Location{uuid:"15"}) CREATE (n1)-[r:`BORN` {date: 188611200000}]->(n2); //1975-12-24
MATCH (n1:Person{uuid:"4"}), (n2:Location{uuid:"16"}) CREATE (n1)-[r:`BORN` {date: 552787200000}]->(n2); //1987-07-09
MATCH (n1:Person{uuid:"5"}), (n2:Location{uuid:"17"}) CREATE (n1)-[r:`BORN` {date: -711763200000}]->(n2); //1947-06-13
MATCH (n1:Person{uuid:"6"}), (n2:Location{uuid:"18"}) CREATE (n1)-[r:`BORN` {date: -751852800000}]->(n2); //1946-03-06
MATCH (n1:Person{uuid:"7"}), (n2:Location{uuid:"19"}) CREATE (n1)-[r:`BORN` {date: -276480000000}]->(n2); //1961-03-29
MATCH (n1:Person{uuid:"8"}), (n2:Location{uuid:"20"}) CREATE (n1)-[r:`BORN` {date: -188611200000}]->(n2); //1964-01-10
// BATTESIMI
MATCH (n1:Person{uuid:"1"}), (n2:Church{uuid:"9"}) CREATE (n1)-[r:`BAPTIZED_IN` {date: 1316108800000}]->(n2); //2011-09-15
MATCH (n1:Person{uuid:"2"}), (n2:Church{uuid:"9"}) CREATE (n1)-[r:`BAPTIZED_IN` {date: 1387734400000}]->(n2); //2013-12-22
// DECESSI direttamente sul nodo Person

// RELAZIONI PER DASHBOARD
MATCH (n1:Person{uuid:"39"}), (n2:Person{uuid:"41"}) CREATE (n1)-[r:`IS_MARRIED_TO` {date: -286848000000}]->(n2); //1960-11-29
MATCH (n1:Person{uuid:"39"}), (n2:Person{uuid:"40"}) CREATE (n1)-[r:`IS_PARENT_OF`]->(n2);
MATCH (n1:Person{uuid:"41"}), (n2:Person{uuid:"40"}) CREATE (n1)-[r:`IS_PARENT_OF`]->(n2);
MATCH (n1:Person{uuid:"41"}), (n2:Location{uuid:"29"}) CREATE (n1)-[r:`BORN` {date: -882835200000}]->(n2); //1942-01-10
MATCH (n1:Person{uuid:"42"}), (n2:Location{uuid:"31"}) CREATE (n1)-[r:`BORN` {date: 596764800000}]->(n2); //1988-11-29


// TODO:
// MATCH (n1:Person{uuid:"5"}), (n2:Person{uuid:"6"}) CREATE (n1)-[r:`IS_MARRIED_TO` {date: }]->(n2);
// MATCH (n1:Person{uuid:"7"}), (n2:Person{uuid:"8"}) CREATE (n1)-[r:`IS_MARRIED_TO` {date: }]->(n2);


