package gift.exchange.interfaces.handler;

import gift.exchange.infrastructure.exception.*;
import gift.exchange.application.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String INTERNAL_SERVER_ERROR_CODE = "INTERNAL_SERVER_ERROR";

    @ExceptionHandler(GiftExchangeException.class)
    public ResponseEntity<ErrorResponse> handleGiftExchangeException(GiftExchangeException ex) {
        logger.error("GiftExchangeException caught: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse("BAD_REQUEST", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(EmailNotificationException.class)
    public ResponseEntity<ErrorResponse> handleEmailNotificationException(EmailNotificationException ex) {
        logger.error("EmailNotificationException caught: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse(INTERNAL_SERVER_ERROR_CODE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(RecipientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRecipientNotFoundException(RecipientNotFoundException ex) {
        logger.error("RecipientNotFoundException caught: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse("NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        logger.error("Unhandled exception caught: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse(INTERNAL_SERVER_ERROR_CODE, "An unexpected error occurred.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(ParticipantNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleParticipantNotFoundException(ParticipantNotFoundException ex) {
        logger.error("ParticipantNotFoundException caught: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse("NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(EmailSendingException.class)
    public ResponseEntity<ErrorResponse> handleEmailSendingException(EmailSendingException ex) {
        logger.error("EmailSendingException caught: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse(INTERNAL_SERVER_ERROR_CODE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}

