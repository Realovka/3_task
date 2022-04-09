package com.company.testtask.service.reader.impl;

import com.company.testtask.service.dto.LoginDto;
import com.company.testtask.service.dto.PostingDto;
import com.company.testtask.service.reader.EntityReader;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

import static com.company.testtask.service.util.DataUtil.*;

@Component
public class LoginReaderImpl implements EntityReader {

    @Override
    public List<LoginDto> createLoginDtos(String pathToFile) {
        try (Reader reader = new FileReader(pathToFile)) {
            return new CsvToBeanBuilder(reader)
                    .withSkipLines(1)
                    .withType(LoginDto.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<PostingDto> createPostingDtos(String pathToFile) {
        try (Reader reader = new FileReader(pathToFile)) {
            return new CsvToBeanBuilder(reader)
                    .withSeparator(DOT_WITH_COMMA_CHAR)
                    .withIgnoreQuotations(true)
                    .withIgnoreEmptyLine(true)
                    .withSkipLines(1)
                    .withType(PostingDto.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
