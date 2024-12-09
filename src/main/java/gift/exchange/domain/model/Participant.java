package gift.exchange.domain.model;

import gift.exchange.infrastructure.persistence.entity.ParticipantEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Participant extends ParticipantEntity {

    private Long id;
    private String name;
    private String email;

    public Participant(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Participant() {
    }

}
