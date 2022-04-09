package com.company.testtask.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponseDto {
    private String application;
    private String appAccountName;
    private boolean isActive;
    private String jobTitle;
    private String department;
}
