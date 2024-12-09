package gift.exchange.infrastructure.exception;

public class EmailNotificationException extends RuntimeException {

    @SuppressWarnings("unused")
    public EmailNotificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailNotificationException(String message) {
        super(message);
    }
}
