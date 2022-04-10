package com.company.testtask.service.mapper.impl;

import com.company.testtask.dao.entity.Posting;
import com.company.testtask.service.dto.PostingResponseDto;
import com.company.testtask.service.mapper.MapperToResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostingResponseMapperAllFieldsImpl implements MapperToResponseDto<List<PostingResponseDto>, List<Posting>> {

    @Override
    public List<PostingResponseDto> mapToResponseDto(List<Posting> postings) {
        return postings.stream().map(posting -> PostingResponseDto.builder()
                .id(posting.getId())
                .matDoc(posting.getMatDoc())
                .item(posting.getItem())
                .docDate(posting.getDocDate())
                .pstngDate(posting.getPstngDate())
                .materialDescription(posting.getMaterialDescription())
                .quantity(posting.getQuantity())
                .bUn(posting.getBUn())
                .amountLC(posting.getAmountLC())
                .crcy(posting.getCrcy())
                .userName(posting.getUserName())
                .authorizedDelivery(posting.isAuthorizedDelivery())
                .build()).collect(Collectors.toList());
    }
}
