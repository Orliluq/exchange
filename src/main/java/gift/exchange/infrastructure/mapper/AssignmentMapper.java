package gift.exchange.infrastructure.mapper;

import gift.exchange.domain.model.Assignment;
import gift.exchange.infrastructure.persistence.entity.AssignmentEntity;

public class AssignmentMapper {

    private AssignmentMapper() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static Assignment toDomain(AssignmentEntity assignmentEntity) {
        if (assignmentEntity == null) {
            return null;
        }
        return new Assignment(
                ParticipantMapper.toDomain(assignmentEntity.getGiver()),
                ParticipantMapper.toDomain(assignmentEntity.getRecipient())
        );
    }
}
