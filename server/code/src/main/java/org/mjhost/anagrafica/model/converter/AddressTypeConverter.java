package org.mjhost.anagrafica.model.converter;

import org.mjhost.anagrafica.model.enumeration.AddressType;
import org.neo4j.ogm.typeconversion.AttributeConverter;

public class AddressTypeConverter implements AttributeConverter<AddressType, String> {
    @Override
    public String toGraphProperty(AddressType addressType) {
        return addressType.name().toLowerCase();
    }

    @Override
    public AddressType toEntityAttribute(String s) {
        return AddressType.valueOf(s.toUpperCase());
    }
}
