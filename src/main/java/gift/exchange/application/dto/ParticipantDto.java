package gift.exchange.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantDto {
    @NotNull(message = "Participant name cannot be null")
    @Size(min = 2, max = 100, message = "Participant name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Participant email cannot be null")
    @Email(message = "Participant email should be valid")
    private String email;

    // Constructor
    public ParticipantDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
