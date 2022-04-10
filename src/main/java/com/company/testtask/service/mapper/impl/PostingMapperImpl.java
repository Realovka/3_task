package com.company.testtask.service.mapper.impl;

import com.company.testtask.dao.entity.Posting;
import com.company.testtask.service.dto.PostingFromFileDto;
import com.company.testtask.service.mapper.Mapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.company.testtask.service.util.DataUtil.COMMA;
import static com.company.testtask.service.util.DataUtil.DATA_FORMATTER;
import static com.company.testtask.service.util.DataUtil.DOT;


@Component
public class PostingMapperImpl implements Mapper<List<Posting>, List<PostingFromFileDto>> {

    @Override
    public List<Posting> mapToEntity(List<PostingFromFileDto> postingFromFileDtos) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATA_FORMATTER);
        return postingFromFileDtos.stream().map(postingDto -> Posting.builder()
                .matDoc(Long.parseLong(postingDto.getMatDoc()))
                .item(Long.parseLong(postingDto.getItem()))
                .docDate(LocalDate.parse(postingDto.getDocDate(), formatter))
                .pstngDate(LocalDate.parse(postingDto.getPstngDate(), formatter))
                .materialDescription(postingDto.getMaterialDescription())
                .quantity(Long.parseLong(postingDto.getQuantity()))
                .bUn(postingDto.getBUn())
                .amountLC(mapToBigDecimal(postingDto.getAmountLC()))
                .crcy(postingDto.getCrcy())
                .userName(postingDto.getUserName())
                .authorizedDelivery(Boolean.parseBoolean(postingDto.getAuthorizedDelivery()))
                .build()).collect(Collectors.toList());
    }

    private BigDecimal mapToBigDecimal(String amountLC) {
         String amountAfterReplaceSymbol = StringUtils.replaceChars(amountLC, COMMA, DOT);
         double amount = Double.parseDouble(amountAfterReplaceSymbol);
         return BigDecimal.valueOf(amount);
    }
}
