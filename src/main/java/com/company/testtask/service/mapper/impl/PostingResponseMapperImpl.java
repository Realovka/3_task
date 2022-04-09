package com.company.testtask.service.mapper.impl;

import com.company.testtask.service.dto.PostingDto;
import com.company.testtask.service.dto.PostingResponseDto;
import com.company.testtask.service.mapper.MapperToResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostingResponseMapperImpl implements MapperToResponseDto<List<PostingResponseDto>, List<PostingDto>> {

    @Override
    public List<PostingResponseDto> mapToResponseDto(List<PostingDto> postingDtos) {
        return postingDtos.stream().map(postingDto -> PostingResponseDto.builder()
                .matDoc(Long.parseLong(postingDto.getMatDoc()))
                .build()).collect(Collectors.toList());
    }

}
