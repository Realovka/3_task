package com.company.testtask.service.mapper.impl;

import com.company.testtask.dao.entity.Login;
import com.company.testtask.service.dto.LoginDto;
import com.company.testtask.service.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoginMapperImpl implements Mapper<List<Login>, List<LoginDto>> {

    @Override
    public List<Login> mapToEntity(List<LoginDto> loginDtos) {
        return loginDtos.stream()
                .map(loginDto -> Login.builder()
                        .application(loginDto.getApplication())
                        .appAccountName(loginDto.getAppAccountName())
                        .isActive(Boolean.parseBoolean(loginDto.getIsActive()))
                        .jobTitle(loginDto.getJobTitle())
                        .department(loginDto.getDepartment())
                        .build()).collect(Collectors.toList());
    }
}
