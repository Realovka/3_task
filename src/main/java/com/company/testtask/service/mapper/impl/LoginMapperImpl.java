package com.company.testtask.service.mapper.impl;

import com.company.testtask.dao.entity.Login;
import com.company.testtask.service.dto.LoginFromFileDto;
import com.company.testtask.service.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoginMapperImpl implements Mapper<List<Login>, List<LoginFromFileDto>> {

    @Override
    public List<Login> mapToEntity(List<LoginFromFileDto> loginFromFileDtos) {
        return loginFromFileDtos.stream()
                .map(loginDto -> Login.builder()
                        .application(loginDto.getApplication())
                        .appAccountName(loginDto.getAppAccountName())
                        .isActive(Boolean.parseBoolean(loginDto.getIsActive()))
                        .jobTitle(loginDto.getJobTitle())
                        .department(loginDto.getDepartment())
                        .build()).collect(Collectors.toList());
    }
}
