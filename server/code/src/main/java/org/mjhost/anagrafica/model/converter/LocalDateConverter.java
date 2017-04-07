package org.mjhost.anagrafica.model.converter;

import org.neo4j.ogm.typeconversion.AttributeConverter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class LocalDateConverter implements AttributeConverter<LocalDate, Long> {

    @Override
    public Long toGraphProperty(LocalDate localDate) {
        return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    @Override
    public LocalDate toEntityAttribute(Long milliseconds) {
        return Instant.ofEpochMilli(milliseconds).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
