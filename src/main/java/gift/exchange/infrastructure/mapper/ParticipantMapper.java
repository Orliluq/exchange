package gift.exchange.infrastructure.mapper;

import gift.exchange.domain.model.Participant;
import gift.exchange.infrastructure.persistence.entity.ParticipantEntity;

public class ParticipantMapper {

    private ParticipantMapper() {
    }

    public static Participant toDomain(ParticipantEntity entity) {
        if (entity == null) {
            return null;
        }
        Participant participant = new Participant();
        participant.setId(entity.getId());
        participant.setName(entity.getName());
        participant.setEmail(entity.getEmail());
        return participant;
    }

    public static ParticipantEntity toEntity(Participant participant) {
        if (participant == null) {
            return null;
        }
        ParticipantEntity entity = new ParticipantEntity();
        entity.setId(participant.getId());
        entity.setName(participant.getName());
        entity.setEmail(participant.getEmail());
        return entity;
    }
}


