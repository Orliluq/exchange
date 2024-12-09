package gift.exchange.interfaces.controller;

import gift.exchange.application.usecase.GenerateAssignmentsUseCase;
import gift.exchange.application.usecase.GetRecipientUseCase;
import gift.exchange.domain.model.Assignment;
import gift.exchange.domain.service.GiftExchangeService;
import gift.exchange.infrastructure.exception.GiftExchangeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    private final GiftExchangeService giftExchangeService;
    private final GenerateAssignmentsUseCase generateAssignmentsUseCase;
    private final GetRecipientUseCase getRecipientUseCase;

    @Autowired
    public AssignmentController(
            GiftExchangeService giftExchangeService,
            GenerateAssignmentsUseCase generateAssignmentsUseCase,
            GetRecipientUseCase getRecipientUseCase
    ) {
        this.giftExchangeService = giftExchangeService;
        this.generateAssignmentsUseCase = generateAssignmentsUseCase;
        this.getRecipientUseCase = getRecipientUseCase;
    }

    @PostMapping("/create")
    public Assignment createAssignment(@RequestBody Assignment assignment) {
        return assignment;
    }

    @PostMapping("/assignGifts")
    public ResponseEntity<String> assignGifts(@RequestBody List<Assignment> assignments) {
        try {
            giftExchangeService.assignGiftsAndNotify(assignments);
            return ResponseEntity.ok("Gifts assigned and emails sent!");
        } catch (GiftExchangeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(MessageFormat.format("Error: {0}", e.getMessage()));
        }
    }

    @GetMapping("/recipient/{participantId}")
    public Assignment getRecipient(@PathVariable Long participantId) {
        return getRecipientUseCase.getRecipient(participantId);
    }

    @PostMapping("/generate")
    public List<Assignment> generateAssignments() {
        return generateAssignmentsUseCase.generateAssignments();
    }
}
