package com.company.testtask.service.trimmer.impl;

import com.company.testtask.service.dto.PostingDto;
import com.company.testtask.service.trimmer.Trimmer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostingTrimmerImpl implements Trimmer<List<PostingDto>> {

    private static final String TABULATION_SYMBOL = "\t";

    @Override
    public List<PostingDto> trimTabulationSymbol(List<PostingDto> postingDtos) {
        return postingDtos.stream().map(postingDto -> PostingDto.builder()
                .matDoc(StringUtils.deleteWhitespace(postingDto.getMatDoc()))
                .item(StringUtils.deleteWhitespace(postingDto.getItem()))
                .docDate(StringUtils.deleteWhitespace(postingDto.getDocDate()))
                .pstngDate(StringUtils.deleteWhitespace(postingDto.getPstngDate()))
                .materialDescription(
                        StringUtils.replaceChars(postingDto.getMaterialDescription(), TABULATION_SYMBOL, StringUtils.EMPTY))
                .quantity(StringUtils.deleteWhitespace(postingDto.getQuantity()))
                .bUn(StringUtils.deleteWhitespace(postingDto.getBUn()))
                .amountLC(StringUtils.deleteWhitespace(postingDto.getAmountLC()))
                .crcy(StringUtils.deleteWhitespace(postingDto.getCrcy()))
                .userName(StringUtils.deleteWhitespace(postingDto.getUserName()))
                .build()).collect(Collectors.toList());
    }
}
