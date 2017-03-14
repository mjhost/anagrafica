// MATRIMONI CELEBRATI NELLA PARROCCHIA OLSON CHURCH


MATCH
  (a:Person)-[m:GOT_MARRIED_AT]->(p:Parish)<-[:GOT_MARRIED_AT]-(b:Person)
WHERE
  a.sex = "M" AND
  b.sex = "F" AND
  p.name = "Olson Church"
RETURN
  a AS groom, b AS bride, m.date AS wedding_date

// MARITO DI Anne Ruiz
MATCH
  (g:Person)-[m:GOT_MARRIED_AT]->(p:Parish)<-[:GOT_MARRIED_AT]-(b:Person)
WHERE
  b.first_name = "Anne" AND
  b.last_name = "Ruiz"
RETURN
  g AS groom