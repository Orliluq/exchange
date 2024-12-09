package gift.exchange.domain.service;

import gift.exchange.infrastructure.exception.ParticipantNotFoundException;
import gift.exchange.infrastructure.persistence.repository.ParticipantRepository;
import gift.exchange.domain.model.Participant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ParticipantServiceTest {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantService participantService;

    @Test
    void shouldReturnParticipantWhenFoundByEmail() {
        // Arrange
        String email = "john.doe@example.com";
        Participant participant = new Participant(1L, "John Doe", email);
        when(participantRepository.findByEmail(email)).thenReturn(Optional.of(participant));

        // Act
        Participant foundParticipant = participantService.findParticipantByEmail(email);

        // Assert
        assertNotNull(foundParticipant);
        assertEquals("John Doe", foundParticipant.getName());
        assertEquals(email, foundParticipant.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenParticipantNotFoundByEmail() {
        // Arrange
        String email = "john.doe@example.com";
        when(participantRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ParticipantNotFoundException.class, () -> participantService.findParticipantByEmail(email));
    }
}
