package gift.exchange.domain.service;

import gift.exchange.application.dto.EmailRequest;
import gift.exchange.domain.model.Assignment;
import gift.exchange.domain.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftExchangeService {

    private final EmailService emailService;

    @Autowired
    public GiftExchangeService(EmailService emailService) {
        this.emailService = emailService;
    }

    private String buildEmailBody(EmailRequest emailRequest) {
        return String.format(
                "Hello %s,%n%nYou are assigned to give a gift to %s (%s).%n%nGift suggestion: %s%n%nHappy Gifting!",
                emailRequest.getSender(),
                emailRequest.getRecipient(),
                emailRequest.getRecipientEmail(),
                emailRequest.getGiftDescription()
        );
    }

    public void assignGiftsAndNotify(List<Assignment> assignments) {
        for (Assignment assignment : assignments) {
            Participant giver = assignment.getGiver();
            Participant recipient = assignment.getRecipient();

            EmailRequest emailRequest = createEmailRequest(giver, recipient);

            emailService.sendEmail(
                    emailRequest.getRecipientEmail(),
                    "Your Gift Exchange Recipient!",
                    buildEmailBody(emailRequest)
            );
        }
    }

    private EmailRequest createEmailRequest(Participant giver, Participant recipient) {
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setSender(giver.getName());
        emailRequest.setRecipient(recipient.getName());
        emailRequest.setRecipientEmail(recipient.getEmail());
        emailRequest.setGiftDescription("A lovely Christmas scarf");
        return emailRequest;
    }
}
