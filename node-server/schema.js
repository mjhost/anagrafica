import { makeExecutableSchema } from 'graphql-tools';
import { v1 as neo4j } from 'neo4j-driver';
import { GraphQLScalarType } from 'graphql';
import { Kind } from 'graphql/language';

const driver = neo4j.driver("bolt://localhost", neo4j.auth.basic("neo4j", "12QWasZX"))

const typeDefs = `

    scalar Date

    enum Sex {
        M
        F
    }

    interface Relation {
        since: Int
    }

    type Location {
        uuid:ID
        country: String
        province: String
        city: String
        street: String
        name: String
        state: String
        zip_code: String
        lng: Float
        lat: Float
    }

    type Birth {
        date: Date
        location: Location
    }

    type Person {
        uuid: ID
        first_name: String!
        last_name: String!
        sex: Sex!
        education_level: String
        title: String
        born: Birth
        years: Int
    }

    type Celebrated {
        age: Int
        person: Person
    }

    type Couple {
        years: Int
        groom: Person
        bride: Person
    }

    type Dashboard {
        date: Date
        birthdays: [Person]
        weddings: [Couple]
        deaths: [Person]
    }

    type Query {
        findByID(uuid: String!, limit: Int): [Person!]
        findByName(name: String!, limit: Int): [Person!]
        dashboard(date: Date): Dashboard
    }

    input LocationInput {
        country: String
        province: String
        city: String
        street: String
        name: String
        state: String
        zip_code: String
        lng: Float
        lat: Float        
    }

    input PersonInput {
        first_name: String!
        last_name: String!
        sex: Sex!
        birthday: Date
        birthplace: LocationInput
    }

    type Mutation {
        addPerson( input: PersonInput ) :Person
    }

    schema {
        query: Query
        mutation: Mutation
    }
`;

//WITH p MATCH (p)-[r]->(t)
//RETURN p, r, t
const resolvers = {
    Date : new GraphQLScalarType({
        name: "Date",
        description: 'Every time you need a date you need a custom scalar :|',
        parseValue(value) {
            return (new Date(value)).getTime();
        },
        serialize(value){
            if(value.toNumber){
                return new Date(value.toNumber());
            }
            return new Date(value);
        },
        parseLiteral(ast){
            if(ast.kind === Kind.INT){
                return parseInt(ast.value, 10);
            }
            return null;
        }
    }),
    Person: {
        years(person){
            let session = driver.session();
            let params = {uuid: person.uuid, now: Date.now()};
            let query = `
MATCH (:Person {uuid:$uuid})-[r:BORN]->(l:Location)
RETURN (toInteger(apoc.date.format($now - r.date, 'ms', 'yyyy', 'CET'))) - 1970 as years
            `;
            return session.run(query, params).then( result => result.records.map(record => record.get("years")));
        },
        born(person){
            let session = driver.session();
            let params = {uuid: person.uuid};
            let query = `
MATCH (:Person {uuid:$uuid})-[r:BORN]->(l:Location)
RETURN {date:r.date, location:l{.*}} as birth
            `;
            return session.run(query, params).then( result => result.records.map(record => record.get("birth"))[0]);
        }
    },
    Mutation: {
        addPerson(_, params){
            let session = driver.session();
            params.input.birthplace = Object.assign({
                address: null, zip_code: null, city: null, province:null, state:null, country: null, lat: null, lng:null
            }, params.input.birthplace)
            let query = `
CREATE (person:Person {first_name:$input.first_name, last_name:$input.last_name, sex:$input.sex, uuid: apoc.create.uuid()})
            `;
            if(params.input.birthday && params.input.birthplace){
                query = `${query}
CREATE (person)-[:BORN {date:$input.birthday}]->(:Location {
    country: $input.birthplace.country, 
    province: $input.birthplace.province,
    city: $input.birthplace.city,
    street: $input.birthplace.street,
    state: $input.birthplace.state,
    zip_code: $input.birthplace.zip_code,
    lng: $input.birthplace.lng,
    lat: $input.birthplace.lat,
    uuid: apoc.create.uuid()
})
                `;
            }
            query = `${query} RETURN person {.*} as person`
            return session.run(query, params).then( result => result.records.map(record => record.get("person"))[0]);            
        }
    },
    Query: {
        findByID(_, params) {
            let session = driver.session();
            let query = `
MATCH (p:Person {uuid: $uuid})
WITH p OPTIONAL MATCH (p)-[born:BORN]->(l:Location)
RETURN p {.*} as person
            `;
            return session.run(query, params).then( result => result.records.map(record => record.get("person")));
        },
        findByName(_, params) {
            let session = driver.session();
            let query = `
MATCH (p:Person)
WHERE
    TOLOWER(p.first_name + " " + p.last_name) CONTAINS(TOLOWER($name))
    OR
    TOLOWER(p.last_name + " " + p.first_name) CONTAINS(TOLOWER($name))
WITH p OPTIONAL MATCH (p)-[born:BORN]->(l:Location)
RETURN p { .* } as person
            `;
            return session.run(query, params).then( result => result.records.map(record => record.get("person")));
        },
        dashboard(_, params) {
            let session = driver.session();
            params.date = params.date || Date.now();
            let dashboard = `
MATCH (born:Person)-[birth:BORN]->(birthPlace:Location)
WHERE 
    apoc.date.format(birth.date, 'ms', 'MMdd', 'CET') = apoc.date.format($date, 'ms', 'MMdd', 'CET')
RETURN
    "birth" as event, born { .*, years: (toInteger(apoc.date.format($date, 'ms', 'yyyy', 'CET')) - toInteger(apoc.date.format(birth.date, 'ms', 'yyyy', 'CET')))}
    as data
UNION
MATCH (dead:Dead)
WHERE
    apoc.date.format(dead.dead_on, 'ms', 'MMdd', 'CET') = apoc.date.format($date, 'ms', 'MMdd', 'CET')
RETURN
    "death" as event, dead { .*, years: (toInteger(apoc.date.format($date, 'ms', 'yyyy', 'CET')) - toInteger(apoc.date.format(dead.dead_on, 'ms', 'yyyy', 'CET')))}
    as data
UNION
MATCH (groom:Person)-[wedding:IS_MARRIED_TO]->(bride:Person)
WHERE
    apoc.date.format(wedding.date, 'ms', 'MMdd', 'CET') = apoc.date.format($date, 'ms', 'MMdd', 'CET')
    AND groom.sex = "M" AND bride.sex = "F"

RETURN
    "wedding" as event, { 
        bride: bride {.*},
        groom: groom {.*},
        years: (toInteger(apoc.date.format($date, 'ms', 'yyyy', 'CET')) - toInteger(apoc.date.format(wedding.date, 'ms', 'yyyy', 'CET')))
    }
    as data
            `;
            return session.run(dashboard, params).then( result => {
                let r = {
                    date: params.date,
                    weddings: result.records.filter(record => record.get("event") === "wedding").map(record => record.get("data")),
                    birthdays: result.records.filter(record => record.get("event") === "birth").map(record => record.get("data")),
                    deaths: result.records.filter(record => record.get("event") === "death").map(record => record.get("data"))
                }; 
                return r;
            });            
        }
    }
}

export default makeExecutableSchema({
    typeDefs,
    resolvers
})
