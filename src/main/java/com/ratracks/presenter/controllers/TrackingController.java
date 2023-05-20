package com.ratracks.presenter.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ratracks.domain.entities.Tracking;
import com.ratracks.domain.enums.Status;
import com.ratracks.domain.usecases.GetTrackingsUseCase;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/trackings")
@AllArgsConstructor
public class TrackingController {

    private final GetTrackingsUseCase getTrackingsUseCase;

    @GetMapping()
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Tracking> getAllTrackings(
            @RequestParam("userId") String userId,
            @RequestParam("status") Status status) {
        return getTrackingsUseCase.execute(new GetTrackingsUseCase.Input(UUID.fromString(userId), status))
                .getTrackings();
    }

}
