package com.company.testtask.service.trimmer.impl;

import com.company.testtask.service.dto.LoginFromFileDto;
import com.company.testtask.service.trimmer.Trimmer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoginTrimmerImpl implements Trimmer<List<LoginFromFileDto>> {

    private static final String TABULATION_SYMBOL = "\t";

    @Override
    public List<LoginFromFileDto> trimTabulationSymbol(List<LoginFromFileDto> dtos) {
        return dtos.stream().map(loginDto -> LoginFromFileDto.builder()
                        .application(loginDto.getApplication())
                        .appAccountName(StringUtils.deleteWhitespace(loginDto.getAppAccountName()))
                        .isActive(StringUtils.deleteWhitespace(loginDto.getIsActive()))
                        .jobTitle(StringUtils.replaceChars(loginDto.getJobTitle(), TABULATION_SYMBOL, StringUtils.EMPTY))
                        .department(StringUtils.replaceChars(loginDto.getDepartment(), TABULATION_SYMBOL, StringUtils.EMPTY))
                        .build())
                .collect(Collectors.toList());
    }
}
