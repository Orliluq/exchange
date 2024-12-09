package gift.exchange.application.usecase;

import gift.exchange.domain.model.Participant;
import gift.exchange.infrastructure.persistence.entity.ParticipantEntity;
import gift.exchange.infrastructure.persistence.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateParticipantUseCase {
    private final ParticipantRepository participantRepository;

    public CreateParticipantUseCase(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public Participant execute(String name, String email) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (email == null || email.isEmpty() || !email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }

        participantRepository.findByEmail(email).ifPresent(_ -> {
            throw new IllegalArgumentException("A participant with this email already exists.");
        });

        ParticipantEntity entity = new ParticipantEntity();
        entity.setName(name);
        entity.setEmail(email);

        ParticipantEntity savedEntity = participantRepository.save(entity);

        return new Participant(savedEntity.getId(), savedEntity.getName(), savedEntity.getEmail());
    }
}
