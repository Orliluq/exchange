package gift.exchange.interfaces.controller;

import gift.exchange.application.usecase.CreateParticipantUseCase;
import gift.exchange.application.usecase.ListParticipantsUseCase;
import gift.exchange.domain.model.Participant;
import gift.exchange.domain.service.ParticipantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    private final CreateParticipantUseCase createParticipantUseCase;
    private final ListParticipantsUseCase listParticipantsUseCase;
    private final ParticipantService participantService;

    public ParticipantController(CreateParticipantUseCase createParticipantUseCase,
                                 ListParticipantsUseCase listParticipantsUseCase,
                                 ParticipantService participantService) {
        this.createParticipantUseCase = createParticipantUseCase;
        this.listParticipantsUseCase = listParticipantsUseCase;
        this.participantService = participantService;
    }

    @PostMapping
    public Participant createParticipant(@Valid @RequestBody Participant participant) {
        return createParticipantUseCase.execute(participant.getName(), participant.getEmail());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Participant> getParticipantByEmail(@PathVariable String email) {
        Participant participant = participantService.findParticipantByEmail(email);
        return ResponseEntity.ok(participant);
    }

    @GetMapping
    public List<Participant> getAllParticipants() {
        return listParticipantsUseCase.listAllParticipants().stream()
                .map(participantEntity -> new Participant(
                        participantEntity.getId(),
                        participantEntity.getName(),
                        participantEntity.getEmail()))
                .toList();
    }
}
