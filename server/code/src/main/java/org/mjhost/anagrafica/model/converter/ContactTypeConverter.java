package org.mjhost.anagrafica.model.converter;

import org.mjhost.anagrafica.model.enumeration.ContactType;
import org.neo4j.ogm.typeconversion.AttributeConverter;

public class ContactTypeConverter implements AttributeConverter<ContactType, String> {
    @Override
    public String toGraphProperty(ContactType contactType) {
        return contactType.name().toLowerCase();
    }

    @Override
    public ContactType toEntityAttribute(String s) {
        return ContactType.valueOf(s.toUpperCase());
    }
}
