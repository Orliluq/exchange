package gift.exchange.infrastructure.persistence.repository;

import gift.exchange.infrastructure.persistence.entity.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<AssignmentEntity, Long> {
    Optional<AssignmentEntity> findByGiverId(Long giverId);
}
