package com.company.testtask.service.writer;

import com.company.testtask.service.dto.PostingFromFileDto;

import java.util.List;

public interface WriterToFile {

    void writeToFile(List<PostingFromFileDto> postingFromFileDtos, String pathToFile);
}
