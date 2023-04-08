package com.ratracks.presenter.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratracks.domain.entities.AnonymousUser;
import com.ratracks.domain.usecases.CreateAnonymousUserUsecase;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final CreateAnonymousUserUsecase createAnonymousUserUsecase;

    @PostMapping("anonymous")
    public AnonymousUser creatAnonymousUser() {
        return createAnonymousUserUsecase.execute(null).getCreatedUser();
    }

}
