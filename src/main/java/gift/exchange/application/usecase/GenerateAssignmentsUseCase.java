package gift.exchange.application.usecase;

import gift.exchange.domain.model.Assignment;
import gift.exchange.domain.model.Participant;
import gift.exchange.domain.service.NotificationService;
import gift.exchange.infrastructure.persistence.entity.AssignmentEntity;
import gift.exchange.infrastructure.persistence.entity.ParticipantEntity;
import gift.exchange.infrastructure.persistence.repository.AssignmentRepository;
import gift.exchange.infrastructure.persistence.repository.ParticipantRepository;
import gift.exchange.infrastructure.mapper.ParticipantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenerateAssignmentsUseCase {
    private static final Logger logger = LoggerFactory.getLogger(GenerateAssignmentsUseCase.class);
    private final ParticipantRepository participantRepository;
    private final AssignmentRepository assignmentRepository;
    private final NotificationService notificationService;

    public GenerateAssignmentsUseCase(ParticipantRepository participantRepository,
                                      AssignmentRepository assignmentRepository,
                                      NotificationService notificationService) {
        this.participantRepository = participantRepository;
        this.assignmentRepository = assignmentRepository;
        this.notificationService = notificationService;
    }

    public List<Assignment> generateAssignments() {
        List<ParticipantEntity> participants = participantRepository.findAll();

        if (participants.size() < 2) {
            throw new IllegalArgumentException("At least two participants are required.");
        }

        return createAssignments(participants);
    }

    private List<Assignment> createAssignments(List<ParticipantEntity> participants) {
        List<Participant> shuffledParticipants = participants.stream()
                .map(ParticipantMapper::toDomain)
                .collect(Collectors.toList());

        Collections.shuffle(shuffledParticipants);

        List<Assignment> assignments = new ArrayList<>();

        for (int i = 0; i < shuffledParticipants.size(); i++) {
            Participant giver = shuffledParticipants.get(i);
            Participant recipient = shuffledParticipants.get((i + 1) % shuffledParticipants.size());

            assignments.add(new Assignment(giver, recipient));

            assignmentRepository.save(new AssignmentEntity(
                    ParticipantMapper.toEntity(giver),
                    ParticipantMapper.toEntity(recipient)
            ));

            sendNotificationToParticipant(giver, recipient);
        }

        return assignments;
    }

    public void sendNotificationToParticipant(Participant giver, Participant recipient) {
        String subject = "Your Secret Santa Recipient!";
        String text = String.format("Hello %s,%n%nYour recipient for the Secret Santa exchange is: %s",
                giver.getName(), recipient.getName());

        try {
            notificationService.sendNotification(giver.getEmail(), subject, text);
        } catch (Exception e) {
            logger.error("Error sending notification to {}: {}", giver.getName(), e.getMessage());
        }
    }
}
