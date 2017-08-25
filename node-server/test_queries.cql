// NATI OGGI
MATCH (born:Person)-[birth:BORN]->(birthPlace:Location)
WHERE 
    apoc.date.format(birth.date, 'ms', 'MMdd') = apoc.date.format($date, 'ms', 'MMdd')
RETURN 
    born { .*, age: (toInteger(apoc.date.format($date, 'ms', 'yyyy')) - toInteger(apoc.date.format(birth.date, 'ms', 'yyyy')))} as birthday

// MORTI OGGI
MATCH (dead:Person)-[death:DEAD]->(deathPlace:Location)
WHERE
    apoc.date.format(death.date, 'ms', 'MMdd') = apoc.date.format($date, 'ms', 'MMdd')
RETURN
    dead { .*, years: (toInteger(apoc.date.format($date, 'ms', 'yyyy')) - toInteger(apoc.date.format(death.date, 'ms', 'yyyy')))} as death

// DASHBOARD
MATCH (born:Person)-[birth:BORN]->(birthPlace:Location)
WHERE 
    apoc.date.format(birth.date, 'ms', 'MMdd', 'CET') = apoc.date.format(1503014400000, 'ms', 'MMdd', 'CET')
RETURN
    "birth" as event, born { .*, years: (toInteger(apoc.date.format(1503014400000, 'ms', 'yyyy', 'CET')) - toInteger(apoc.date.format(birth.date, 'ms', 'yyyy', 'CET')))}
    as data
UNION
MATCH (dead:Person)-[death:DEAD]->(deathPlace:Location)
WHERE
    apoc.date.format(death.date, 'ms', 'MMdd', 'CET') = apoc.date.format(1491955200000, 'ms', 'MMdd', 'CET')
RETURN
    "death" as event, dead { .*, years: (toInteger(apoc.date.format(1491955200000, 'ms', 'yyyy', 'CET')) - toInteger(apoc.date.format(death.date, 'ms', 'yyyy', 'CET')))}
    as data
UNION
MATCH (groom:Person)-[wedding:IS_MARRIED_TO]->(bride:Person)
WHERE
    apoc.date.format(wedding.date, 'ms', 'MMdd', 'CET') = apoc.date.format(1515542400000, 'ms', 'MMdd', 'CET')
    AND groom.sex = "M" AND bride.sex = "F"

RETURN
    "wedding" as event, { 
        bride: bride,
        groom: groom,
        years: (toInteger(apoc.date.format(1515542400000, 'ms', 'yyyy', 'CET')) - toInteger(apoc.date.format(wedding.date, 'ms', 'yyyy', 'CET')))
    }
    as data




RETURN apoc.date.format(1334181600000, 'ms', 'MMdd', 'CET'), apoc.date.format(1491955200000, 'ms', 'MMdd', 'CET')
new Date(1334181600000);
new Date()