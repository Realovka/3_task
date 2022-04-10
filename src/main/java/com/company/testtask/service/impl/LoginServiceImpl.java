package com.company.testtask.service.impl;

import com.company.testtask.dao.entity.Login;
import com.company.testtask.dao.repository.LoginRepository;
import com.company.testtask.service.LoginService;
import com.company.testtask.service.dto.LoginFromFileDto;
import com.company.testtask.service.dto.LoginResponseDto;
import com.company.testtask.service.mapper.Mapper;
import com.company.testtask.service.mapper.MapperToResponseDto;
import com.company.testtask.service.reader.EntityReader;
import com.company.testtask.service.trimmer.Trimmer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private static final String PATH_TO_FILE = "src/main/resources/files/logins.csv";

    private final LoginRepository loginRepository;
    private final EntityReader entityReader;
    private final Trimmer<List<LoginFromFileDto>> loginTrimmer;
    private final Mapper<List<Login>, List<LoginFromFileDto>> loginMapper;
    private final MapperToResponseDto<List<LoginResponseDto>, List<LoginFromFileDto>> mapperToResponse;

    @Override
    public List<LoginResponseDto> findAll() {
        List<LoginFromFileDto> loginFromFileDtos = entityReader.createLoginDtos(PATH_TO_FILE);
        loginFromFileDtos = loginTrimmer.trimTabulationSymbol(loginFromFileDtos);
        return mapperToResponse.mapToResponseDto(loginFromFileDtos);
    }

    @Override
    public void saveInDb() {
        List<LoginFromFileDto> loginFromFileDtos = entityReader.createLoginDtos(PATH_TO_FILE);
        List<Login> logins = loginMapper.mapToEntity(loginFromFileDtos);
        loginRepository.saveAll(logins);
    }
}
