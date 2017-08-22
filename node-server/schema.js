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
        id: ID!
        first_name: String!
        last_name: String!
        sex: Sex!
        education_level: String
        title: String
        born: Birth
        age: Int
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
        date: Date!
        birthdays: [Person]!
        weddings: [Couple]!
        deaths: [Person]!
    }

    type Query {
        findByName(name: String!, limit: Int): [Person!]
        dashboard(date: Date): Dashboard!
    }

    schema {
        query: Query
    }
`;

//WITH p MATCH (p)-[r]->(t)
//RETURN p, r, t
const resolvers = {
    Date : new GraphQLScalarType({
        name: "Date",
        description: 'Every time you need a date you need a custom scalar:|',
        parseValue(value) {
            console.log('parseValue', value);
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
                console.log('parseLiteral', ast.value);
                return parseInt(ast.value, 10);
            }
            return null;
        }
    }),
    Query: {
        findByName(_, params) {
            let session = driver.session();
            let query = `
MATCH (p:Person)
WHERE
    TOLOWER(p.first_name + " " + p.last_name) CONTAINS(TOLOWER($name))
    OR
    TOLOWER(p.last_name + " " + p.first_name) CONTAINS(TOLOWER($name))
WITH p MATCH (p)-[born:BORN]->(l:Location)
RETURN p { .*, id: ID(p), born: { date: born.date, location: l {.*} } } as person
            `;
            return session.run(query, params).then( result => result.records.map(record => record.get("person")));
        },
        dashboard(_, params) {
            let session = driver.session();
            params.date = params.date || Date.now();
            let birthdays = `
MATCH (born:Person)-[birth:BORN]->(birthPlace:Location)
WHERE 
    apoc.date.format(birth.date, 'ms', 'MMdd') = apoc.date.format($date, 'ms', 'MMdd')
RETURN 
    born { .*, age: (toInteger(apoc.date.format($date, 'ms', 'yyyy')) - toInteger(apoc.date.format(birth.date, 'ms', 'yyyy')))} as birthday
            `;
            let deaths = `
MATCH (dead:Person)-[death:DEAD]->(deathPlace:Location)
WHERE
    apoc.date.format(death.date, 'ms', 'MMdd') = apoc.date.format($date, 'ms', 'MMdd')
RETURN
    dead { .*, years: (toInteger(apoc.date.format($date, 'ms', 'yyyy')) - toInteger(apoc.date.format(death.date, 'ms', 'yyyy')))} as death
            `;
            session.run(birthdays, params).then( result => {
                return {
                    date: params.date,
                    birthdays: result.records.map(record => record.get("birthday")),
                    deaths: result.records.map(record => record.get("death"))
                };
            });            
        }
    }
}

export default makeExecutableSchema({
    typeDefs,
    resolvers
})
