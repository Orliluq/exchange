package gift.exchange.domain.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Setter
@Getter
public class Assignment {

    @NotNull
    private final Participant giver;

    @NotNull
    private final Participant recipient;

    public Assignment(Participant giver, Participant recipient) {
        if (giver == null || recipient == null) {
            throw new IllegalArgumentException("Giver and recipient must not be null");
        }
        this.giver = giver;
        this.recipient = recipient;
    }
}
