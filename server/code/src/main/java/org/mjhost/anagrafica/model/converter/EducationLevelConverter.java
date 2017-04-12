package org.mjhost.anagrafica.model.converter;

import org.mjhost.anagrafica.model.enumeration.EducationLevel;
import org.neo4j.ogm.typeconversion.AttributeConverter;

public class EducationLevelConverter implements AttributeConverter<EducationLevel, String> {
    @Override
    public String toGraphProperty(EducationLevel educationLevel) {
        return educationLevel.name().toLowerCase();
    }

    @Override
    public EducationLevel toEntityAttribute(String s) {
        return EducationLevel.valueOf(s.toUpperCase());
    }
}
