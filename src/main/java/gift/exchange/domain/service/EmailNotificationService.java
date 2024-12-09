package gift.exchange.domain.service;

import gift.exchange.domain.model.Assignment;
import jakarta.mail.MessagingException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class EmailNotificationService extends NotificationService {

    public EmailNotificationService(JavaMailSender javaMailSender) {
        super(javaMailSender);
    }

    @Override
    public void notifyAssignment(Assignment assignment) {
        String to = assignment.getRecipient().getEmail();
        String subject = "Your Gift Assignment";
        String text = MessageFormat.format("You have been assigned to give a gift to {0}", assignment.getGiver().getName());
        try {
            sendNotification(to, subject, text);
        } catch (MessagingException e) {
            logger.error("Failed to send notification for assignment: {}", assignment, e);
        }
    }
}