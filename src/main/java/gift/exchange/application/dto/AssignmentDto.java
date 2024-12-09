package gift.exchange.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignmentDto {
    @NotNull(message = "Giver name cannot be null")
    @Size(min = 2, max = 100, message = "Giver name must be between 2 and 100 characters")
    private String giverName;

    @NotNull(message = "Recipient name cannot be null")
    @Size(min = 2, max = 100, message = "Recipient name must be between 2 and 100 characters")
    private String recipientName;

    // Constructor
    public AssignmentDto(String giverName, String recipientName) {
        this.giverName = giverName;
        this.recipientName = recipientName;
    }
}
