package gift.exchange.domain.service;

import gift.exchange.domain.model.Assignment;
import gift.exchange.domain.model.Participant;
import gift.exchange.infrastructure.exception.EmailNotificationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GiftExchangeServiceTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private GiftExchangeService giftExchangeService;

    @Test
    void shouldAssignGiftsAndSendEmails() {
        // Arrange
        Participant giver = new Participant(1L, "John Doe", "john.doe@example.com");
        Participant recipient = new Participant(2L, "Jane Doe", "jane.doe@example.com");

        Assignment assignment = new Assignment(giver, recipient);
        List<Assignment> assignments = List.of(assignment);

        // Act
        giftExchangeService.assignGiftsAndNotify(assignments);

        // Assert
        verify(emailService, times(1)).sendEmail(
                eq(recipient.getEmail()),
                eq("Your Gift Exchange Recipient!"),
                anyString()
        );
    }

    @Test
    void shouldThrowExceptionWhenEmailSendingFails() {
        // Arrange
        Participant giver = new Participant(1L, "John Doe", "john.doe@example.com");
        Participant recipient = new Participant(2L, "Jane Doe", "jane.doe@example.com");

        Assignment assignment = new Assignment(giver, recipient);
        List<Assignment> assignments = List.of(assignment);
        doThrow(new EmailNotificationException("Email sending failed"))
                .when(emailService).sendEmail(anyString(), anyString(), anyString());

        // Act & Assert
        assertThrows(EmailNotificationException.class, () -> giftExchangeService.assignGiftsAndNotify(assignments));
    }
}
