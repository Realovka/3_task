package com.company.testtask.service.reader;

import com.company.testtask.service.dto.LoginDto;
import com.company.testtask.service.dto.PostingDto;

import java.util.List;

public interface EntityReader {

    List<LoginDto> createLoginDtos(String pathToFile);

    List<PostingDto> createPostingDtos(String pathToFile);
}
