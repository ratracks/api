package com.ratracks.presenter.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ratracks.domain.entities.tracking.Tracking;
import com.ratracks.domain.enums.Status;
import com.ratracks.domain.enums.Transporter;
import com.ratracks.domain.usecases.CreateTrackingUseCase;
import com.ratracks.domain.usecases.GetTrackingsUseCase;
import com.ratracks.presenter.dtos.CreateTrackingDto;
import com.ratracks.presenter.dtos.TrackingDto;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/trackings")
@AllArgsConstructor
public class TrackingController {

    private final GetTrackingsUseCase getTrackingsUseCase;

    private final CreateTrackingUseCase createTrackingUseCase;

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<TrackingDto> getAllTrackings(
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "status", defaultValue = "in_progress") String status) {
        List<Tracking> trackings = getTrackingsUseCase
                .execute(new GetTrackingsUseCase.Input(UUID.fromString(userId), Status.valueOf(status.toUpperCase())))
                .getTrackings();

        return trackings
                .stream()
                .map(t -> new TrackingDto(t.getId().toString(), t.getCreatedAt().toString(),
                        t.getUpdatedAt().toString(),
                        t.getProductName(), t.getTrackingCode().getCode(), t.getTransporter().toString(),
                        t.getStatus().toString(), t.getUserId().toString()))
                .toList();
    }

    @PostMapping
    public Tracking createTracking(@RequestBody CreateTrackingDto params) {
        return createTrackingUseCase
                .execute(new CreateTrackingUseCase.Input(params.getProductName(), params.getTrackingCode(),
                        Transporter.CORREIOS, Status.IN_PROGRESS, UUID.fromString(params.getUserId())))
                .getCreatedTracking();
    }
}
