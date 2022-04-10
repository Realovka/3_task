package com.company.testtask.service.reader;

import com.company.testtask.service.dto.LoginFromFileDto;
import com.company.testtask.service.dto.PostingFromFileDto;

import java.util.List;

public interface EntityReader {

    List<LoginFromFileDto> createLoginDtos(String pathToFile);

    List<PostingFromFileDto> createPostingDtos(String pathToFile);
}
