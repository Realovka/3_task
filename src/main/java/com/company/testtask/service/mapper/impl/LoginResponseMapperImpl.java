package com.company.testtask.service.mapper.impl;

import com.company.testtask.service.dto.LoginDto;
import com.company.testtask.service.dto.LoginResponseDto;
import com.company.testtask.service.mapper.MapperToResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoginResponseMapperImpl implements MapperToResponseDto<List<LoginResponseDto>, List<LoginDto>> {

    @Override
    public List<LoginResponseDto> mapToResponseDto(List<LoginDto> dtos) {
        return dtos.stream().map(loginDto -> LoginResponseDto.builder()
                .application(loginDto.getApplication())
                .appAccountName(loginDto.getAppAccountName())
                .isActive(Boolean.parseBoolean(loginDto.getIsActive()))
                .jobTitle(loginDto.getJobTitle())
                .department(loginDto.getDepartment())
                .build()).collect(Collectors.toList());
    }
}
