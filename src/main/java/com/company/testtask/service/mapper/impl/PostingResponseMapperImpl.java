package com.company.testtask.service.mapper.impl;

import com.company.testtask.service.dto.PostingDto;
import com.company.testtask.service.dto.PostingFromFileDto;
import com.company.testtask.service.mapper.MapperToResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostingResponseMapperImpl implements MapperToResponseDto<List<PostingDto>, List<PostingFromFileDto>> {

    @Override
    public List<PostingDto> mapToResponseDto(List<PostingFromFileDto> postingFromFileDtos) {
        return postingFromFileDtos.stream().map(postingDto -> PostingDto.builder()
                .matDoc(Long.parseLong(postingDto.getMatDoc()))
                .build()).collect(Collectors.toList());
    }
}
