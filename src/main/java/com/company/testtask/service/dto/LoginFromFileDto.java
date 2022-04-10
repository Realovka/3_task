package com.company.testtask.service.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginFromFileDto {
    @CsvBindByPosition(position = 0)
    private String application;
    @CsvBindByPosition(position = 1)
    private String appAccountName;
    @CsvBindByPosition(position = 2)
    private String isActive;
    @CsvBindByPosition(position = 3)
    private String jobTitle;
    @CsvBindByPosition(position = 4)
    private String department;
}
