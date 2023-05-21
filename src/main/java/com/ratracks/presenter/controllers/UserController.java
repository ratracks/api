package com.ratracks.presenter.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ratracks.domain.entities.AnonymousUser;
import com.ratracks.domain.usecases.CreateAnonymousUserUsecase;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final CreateAnonymousUserUsecase createAnonymousUserUsecase;

    @PostMapping("anonymous")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public AnonymousUser creatAnonymousUser() {
        return createAnonymousUserUsecase.execute(null).getCreatedUser();
    }

}
