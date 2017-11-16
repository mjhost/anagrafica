import { GraphQLScalarType } from 'graphql';
import { Kind } from 'graphql/language';

const DateType = new GraphQLScalarType({
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
});

export { DateType };