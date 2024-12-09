package gift.exchange.application.usecase;

import gift.exchange.domain.model.Assignment;
import gift.exchange.infrastructure.exception.RecipientNotFoundException;
import gift.exchange.infrastructure.mapper.AssignmentMapper;
import gift.exchange.infrastructure.persistence.repository.AssignmentRepository;
import org.springframework.stereotype.Service;

@Service
public class GetRecipientUseCase {

    private final AssignmentRepository assignmentRepository;

    public GetRecipientUseCase(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public Assignment getRecipient(Long participantId) {
        return assignmentRepository.findByGiverId(participantId)
                .map(AssignmentMapper::toDomain)
                .orElseThrow(() -> new RecipientNotFoundException(
                        String.format("No recipient found for participant ID: %d", participantId))
                );
    }
}
