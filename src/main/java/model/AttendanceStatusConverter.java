package model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import model.enums.AttendanceStatus;

@Converter(autoApply = true)
public class AttendanceStatusConverter implements AttributeConverter<AttendanceStatus, String> {
    @Override
    public String convertToDatabaseColumn(AttendanceStatus status) {
        return status == null ? null : status.name();
    }

    @Override
    public AttendanceStatus convertToEntityAttribute(String dbData) {
        return dbData == null ? null : AttendanceStatus.valueOf(dbData);
    }
}
