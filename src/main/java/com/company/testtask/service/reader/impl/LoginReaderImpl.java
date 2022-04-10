package com.company.testtask.service.reader.impl;

import com.company.testtask.service.dto.LoginFromFileDto;
import com.company.testtask.service.dto.PostingFromFileDto;
import com.company.testtask.service.reader.EntityReader;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

import static com.company.testtask.service.util.DataUtil.*;

@Component
public class LoginReaderImpl implements EntityReader {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<LoginFromFileDto> createLoginDtos(String pathToFile) {
        try (Reader reader = new FileReader(pathToFile)) {
            return new CsvToBeanBuilder(reader)
                    .withSkipLines(1)
                    .withType(LoginFromFileDto.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while reading from " + pathToFile + " file " + e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<PostingFromFileDto> createPostingDtos(String pathToFile) {
        try (Reader reader = new FileReader(pathToFile)) {
            return new CsvToBeanBuilder(reader)
                    .withSeparator(DOT_WITH_COMMA_CHAR)
                    .withIgnoreQuotations(true)
                    .withIgnoreEmptyLine(true)
                    .withSkipLines(1)
                    .withType(PostingFromFileDto.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while reading from " + pathToFile + " file " + e);
            return Collections.emptyList();
        }
    }
}
