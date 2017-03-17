// MATRIMONI CELEBRATI NELLA PARROCCHIA OLSON CHURCH


MATCH
  (g:Person)-[mg:GOT_MARRIED_AT]->(p:Parish)<-[mb:GOT_MARRIED_AT]-(b:Person)
WHERE
  g.sex = "M" AND
  b.sex = "F" AND
  p.name = "Olson Church" AND
  mg.document_record = mb.document_record
RETURN
  g AS groom, b AS bride, mg.date AS wedding_date

// MARITO DI Anne Ruiz
MATCH
  (g:Person)-[mg:GOT_MARRIED_AT]->(p:Parish)<-[mb:GOT_MARRIED_AT]-(b:Person)
WHERE
  b.first_name = "Anne" AND b.last_name = "Ruiz" AND
  mg.document_record = mb.document_record
RETURN
  g AS groom