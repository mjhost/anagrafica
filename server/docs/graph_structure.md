**NODES**

Person (label)
* first_name
* last_name
* sex
* birth_date
* birth_place
* death_date
* death_place
* job_title
* education_level
* hobbies
* address_street
* address_zip
* address_province
* address_state
* address_country
* home_phone
* mobile_phone
* email

TO BE COMPLETED


* creation_date
* update_date
    
---

Parish (label)
* name
* description
* address_street
* address_zip
* address_province
* address_state
* address_country
* home_phone
* email
* creation_date
* update_date

---

Organization (label)
* name
* description
* creation_date
* update_date

---

Event (label)
* name
* description
* date
* place
* creation_date
* update_date

---
---

**EDGES**

* (x:Person)-[:GOT_MARRIED_AT{description:d,date:d}]->(p:Parish)




* (x:Person)-[:BELONGS_TO{since_date:d,role:r}]->(y:Parish)
* (x:Person)-[:BELONGS_TO{since_date:d,role:r}]->(z:Organization)
* (x:Person)-[:WAS_BAPTIZED{}]->(e:Event)

* (x:Person)-[:IS_PARENT_OF{}]->(y:Person)
* (x:Person)-[:IS_CHILD_OF{}]->(y:Person)
* (x:Person)-[:IS_LEGAL_GUARDIAN_OF{}]->(y:Person)
* (x:Person)-[:IS_CAREGIVER_OF{}]->(y:Person)
* (x:Person)-[:IS_MARRIED_TO{}]->(y:Person)
* (x:Person)-[:IS_RELATIVE_OF{role:r}]->(y:Person)
* (x:Person)-[:IS_GODPARENT_OF{}]->(y:Person)
* (x:Person)-[:IS_WEDDING_WITNESS_OF{}]->(y:Person)