package gift.exchange.domain.service;

import gift.exchange.infrastructure.persistence.repository.ParticipantRepository;
import gift.exchange.infrastructure.exception.ParticipantNotFoundException;
import gift.exchange.domain.model.Participant;
import gift.exchange.infrastructure.mapper.ParticipantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public Participant findParticipantByEmail(String email) {
        return participantRepository.findByEmail(email)
                .map(ParticipantMapper::toDomain)
                .orElseThrow(() -> new ParticipantNotFoundException(MessageFormat.format("Participant not found with email: {0}", email)));
    }
}
