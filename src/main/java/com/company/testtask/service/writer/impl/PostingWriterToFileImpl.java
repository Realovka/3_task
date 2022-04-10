package com.company.testtask.service.writer.impl;

import com.company.testtask.service.dto.PostingFromFileDto;
import com.company.testtask.service.util.ColumnNameList;
import com.company.testtask.service.writer.WriterToFile;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.company.testtask.service.util.DataUtil.AUTHORIZED_DELIVERY;
import static com.company.testtask.service.util.DataUtil.DOT_WITH_COMMA;

@Component
public class PostingWriterToFileImpl implements WriterToFile {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void writeToFile(List<PostingFromFileDto> postingFromFileDtos, String pathToFile) {
        try {
            List<String> columnNames = ColumnNameList.columnNames;
            FileWriter writer = new FileWriter(pathToFile);
            columnNames.forEach(columnName -> {
                try {
                    writer.append(columnName).append(DOT_WITH_COMMA);
                } catch (IOException e) {
                    logger.log(Level.ERROR, "Error while writing to " + pathToFile + " file " + e);
                }
            });
            writer.append(AUTHORIZED_DELIVERY);
            writer.append(StringUtils.LF);
            writer.append(StringUtils.LF);
            postingFromFileDtos.forEach(postingDto -> {
                try {
                    writer.append(postingDto.getMatDoc());
                    writer.append(DOT_WITH_COMMA);
                    writer.append(postingDto.getItem());
                    writer.append(DOT_WITH_COMMA);
                    writer.append(postingDto.getDocDate());
                    writer.append(DOT_WITH_COMMA);
                    writer.append(postingDto.getPstngDate());
                    writer.append(DOT_WITH_COMMA);
                    writer.append(postingDto.getMaterialDescription());
                    writer.append(DOT_WITH_COMMA);
                    writer.append(postingDto.getQuantity());
                    writer.append(DOT_WITH_COMMA);
                    writer.append(postingDto.getBUn());
                    writer.append(DOT_WITH_COMMA);
                    writer.append(postingDto.getAmountLC());
                    writer.append(DOT_WITH_COMMA);
                    writer.append(postingDto.getCrcy());
                    writer.append(DOT_WITH_COMMA);
                    writer.append(postingDto.getUserName());
                    writer.append(DOT_WITH_COMMA);
                    writer.append(postingDto.getAuthorizedDelivery());
                    writer.append(StringUtils.LF);
                } catch (IOException e) {
                    logger.log(Level.ERROR, "Error while writing to " + pathToFile + " file " + e);
                }
            });
            writer.flush();
            writer.close();
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while writing to " + pathToFile + " file " + e);
        }
    }
}
