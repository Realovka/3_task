package com.company.testtask.service.mapper;

public interface MapperToResponseDto<R, D> {

    R mapToResponseDto(D d);
}
