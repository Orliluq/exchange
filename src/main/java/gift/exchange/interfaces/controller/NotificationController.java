package gift.exchange.interfaces.controller;

import gift.exchange.application.dto.EmailRequest;
import gift.exchange.domain.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final EmailService emailService;

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send/email")
    public void sendEmailNotification(@RequestBody EmailRequest request) {
        String subject = "Your Gift Exchange Recipient!";
        String body = "Hi %s,\n\nYou have been assigned to give a gift to %s (%s).\n\nHappy gifting!".formatted(request.getSender(), request.getRecipient(), request.getRecipientEmail());
        emailService.sendEmail(request.getRecipientEmail(), subject, body);
    }
}