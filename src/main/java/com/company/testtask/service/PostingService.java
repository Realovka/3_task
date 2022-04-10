package com.company.testtask.service;

import com.company.testtask.service.dto.PostingDto;
import com.company.testtask.service.dto.PostingResponseDto;

import java.util.List;

public interface PostingService {

    List<PostingDto> findAll();

    void saveInFile();

    void saveInDb();

    List<PostingResponseDto> findByPeriod(String dataStart, String dataEnd, boolean isAuthorized);
}
