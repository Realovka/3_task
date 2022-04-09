package com.company.testtask.service.mapper;

public interface Mapper <E, D> {

    E mapToEntity(D d);
}
