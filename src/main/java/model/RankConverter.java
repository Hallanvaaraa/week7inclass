package model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import model.enums.Rank;

@Converter(autoApply = true)
public class RankConverter implements AttributeConverter<Rank, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Rank rank) {
        return rank == null ? null : rank.level;
    }

    @Override
    public Rank convertToEntityAttribute(Integer dbData) {
        return Rank.fromLevel(dbData);
    }
}
