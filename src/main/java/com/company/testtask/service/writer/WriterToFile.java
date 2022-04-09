package com.company.testtask.service.writer;

import com.company.testtask.service.dto.PostingDto;

import java.util.List;

public interface WriterToFile {

    void writeToFile(List<PostingDto> postingDtos, String pathToFile);
}
