package gift.exchange.infrastructure.persistence.repository;

import gift.exchange.infrastructure.persistence.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
    Optional<ParticipantEntity> findByEmail(String email);
}
