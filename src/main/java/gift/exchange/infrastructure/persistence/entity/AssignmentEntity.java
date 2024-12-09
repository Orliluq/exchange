package gift.exchange.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("ALL")
@Setter
@Getter
@Entity
@Table(name = "assignments")
public class AssignmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "giver_id")
    private ParticipantEntity giver;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private ParticipantEntity recipient;

    protected AssignmentEntity() {
    }

    public AssignmentEntity(ParticipantEntity giver, ParticipantEntity recipient) {
        this.giver = giver;
        this.recipient = recipient;
    }
}

