package gift.exchange.domain.service;

import gift.exchange.domain.model.Assignment;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@SuppressWarnings("ALL")
@Getter
public abstract class NotificationService {

    public static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final JavaMailSender javaMailSender;

    protected NotificationService(JavaMailSender javaMailSender) {
        if (javaMailSender == null) {
            throw new IllegalArgumentException("JavaMailSender cannot be null");
        }
        this.javaMailSender = javaMailSender;
    }

    public void sendNotification(String to, String subject, String text) throws MessagingException {
        final int maxRetries = 3;

        for (int attempts = 1; true; attempts++) {
            try {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setTo(to);
                helper.setSubject(subject);
                helper.setText(text, true);

                javaMailSender.send(mimeMessage);
                logger.info("Email successfully sent to {}", to);
                return;
            } catch (Exception e) {
                logger.warn("Attempt {} to send email to {} failed.", attempts, to, e);

                if (attempts == maxRetries) {
                    logger.error("All attempts to send email to {} failed.", to);
                    throw new MessagingException("Failed to send email after %d attempts.".formatted(maxRetries), e);
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new MessagingException("Email sending was interrupted.", ie);
                }
            }
        }
    }

    public void notifyAssignment(Assignment assignment) {
        logger.info("Notifying about assignment: {}", assignment);
    }

}
