package com.company.testtask.service;

import com.company.testtask.service.dto.LoginResponseDto;

import java.util.List;

public interface LoginService {

    List<LoginResponseDto> findAll();

    void saveInDb();
}
