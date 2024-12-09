package gift.exchange.application.usecase;

import gift.exchange.infrastructure.persistence.entity.ParticipantEntity;
import gift.exchange.infrastructure.persistence.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListParticipantsUseCase {

    private final ParticipantRepository participantRepository;

    public ListParticipantsUseCase(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public List<ParticipantEntity> listAllParticipants()  {
        return participantRepository.findAll();
    }
}
