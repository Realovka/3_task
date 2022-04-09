package com.company.testtask.service.writer.impl;

import com.company.testtask.service.dto.PostingDto;
import com.company.testtask.service.writer.WriterToFile;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.company.testtask.service.util.DataUtil.*;

@Component
public class PostingWriterToFileImpl implements WriterToFile {

    @Override
    public void writeToFile(List<PostingDto> postingDtos, String pathToFile) {
        try {
            FileWriter writer = new FileWriter(pathToFile);
            writer.append(MAT_DOC);
            writer.append(DOT_WITH_COMMA);
            writer.append(ITEM);
            writer.append(DOT_WITH_COMMA);
            writer.append(DOC_DATE);
            writer.append(DOT_WITH_COMMA);
            writer.append(PSTG_DATE);
            writer.append(DOT_WITH_COMMA);
            writer.append(MATERIAL_DESCRIPTION);
            writer.append(DOT_WITH_COMMA);
            writer.append(QUANTITY);
            writer.append(DOT_WITH_COMMA);
            writer.append(BUN);
            writer.append(DOT_WITH_COMMA);
            writer.append(AMOUNT_LC);
            writer.append(DOT_WITH_COMMA);
            writer.append(CRCY);
            writer.append(DOT_WITH_COMMA);
            writer.append(USER_NAME);
            writer.append(DOT_WITH_COMMA);
            writer.append(AUTHORIZED_DELIVERY);
            writer.append(StringUtils.LF);
            writer.append(StringUtils.LF);
            postingDtos.forEach(postingDto -> {
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
                    e.printStackTrace();
                }
            });
            writer.flush();
            writer.close();
        } catch (IOException e) {

        }
    }
}
