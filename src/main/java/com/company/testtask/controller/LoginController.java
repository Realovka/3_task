package com.company.testtask.controller;

import com.company.testtask.service.LoginService;
import com.company.testtask.service.dto.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logins")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;


    @GetMapping
    public List<LoginResponseDto> findAll() {
       return loginService.findAll();
    }

    @PostMapping
    public void saveInDb() {
        loginService.saveInDb();
    }
}
