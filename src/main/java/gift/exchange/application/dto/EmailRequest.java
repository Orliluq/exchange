package gift.exchange.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequest {
    @NotNull(message = "Sender name cannot be null")
    @Size(min = 2, max = 100, message = "Sender name must be between 2 and 100 characters")
    private String sender;

    @NotNull(message = "Recipient name cannot be null")
    @Size(min = 2, max = 100, message = "Recipient name must be between 2 and 100 characters")
    private String recipient;

    @NotNull(message = "Recipient email cannot be null")
    @Email(message = "Recipient email should be valid")
    private String recipientEmail;

    @Size(max = 255, message = "Gift description must not exceed 255 characters")
    private String giftDescription;
}
