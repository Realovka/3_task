package com.company.testtask.service.impl;

import com.company.testtask.dao.entity.Login;
import com.company.testtask.dao.repository.LoginRepository;
import com.company.testtask.service.dto.LoginFromFileDto;
import com.company.testtask.service.dto.LoginResponseDto;
import com.company.testtask.service.mapper.Mapper;
import com.company.testtask.service.mapper.MapperToResponseDto;
import com.company.testtask.service.reader.EntityReader;
import com.company.testtask.service.trimmer.Trimmer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

    private LoginServiceImpl loginService;
    @Mock
    private LoginRepository loginRepository;
    @Mock
    private EntityReader entityReader;
    @Mock
    private Trimmer<List<LoginFromFileDto>> loginTrimmer;
    @Mock
    private Mapper<List<Login>, List<LoginFromFileDto>> loginMapper;
    @Mock
    private MapperToResponseDto<List<LoginResponseDto>, List<LoginFromFileDto>> mapperToResponse;
    private String PATH_TO_FILE;
    private List<LoginFromFileDto> loginFromFileDtos;
    private List<LoginFromFileDto> afterTrimmer;
    private List<LoginResponseDto> response;
    private List<Login> logins;

    @BeforeEach
    void setUp() {
        loginService = new LoginServiceImpl(loginRepository, entityReader, loginTrimmer, loginMapper, mapperToResponse);
        PATH_TO_FILE = "src/main/resources/files/logins.csv";
        loginFromFileDtos = List.of(LoginFromFileDto.builder()
                .application("SAP")
                .appAccountName("\tBORODZUTPNI")
                .isActive("\tTrue")
                .jobTitle("\tКомплектовщик товаров")
                .department("\tГруппа складского хозяйства")
                .build());
        afterTrimmer = List.of(LoginFromFileDto.builder()
                .application("SAP")
                .appAccountName("BORODZUTPNI")
                .isActive("True")
                .jobTitle("Комплектовщик товаров")
                .department("Группа складского хозяйства")
                .build());
        response = List.of(LoginResponseDto.builder()
                .application("SAP")
                .appAccountName("BORODZUTPNI")
                .isActive(true)
                .jobTitle("Комплектовщик товаров")
                .department("Группа складского хозяйства")
                .build());
        logins = List.of(Login.builder()
                .application("SAP")
                .appAccountName("BORODZUTPNI")
                .isActive(true)
                .jobTitle("Комплектовщик товаров")
                .department("Группа складского хозяйства")
                .build());
    }

    @Test
    public void findAllTest() {
        when(entityReader.createLoginDtos(PATH_TO_FILE)).thenReturn(loginFromFileDtos);
        when(loginTrimmer.trimTabulationSymbol(loginFromFileDtos)).thenReturn(afterTrimmer);
        when(mapperToResponse.mapToResponseDto(afterTrimmer)).thenReturn(response);
        List<LoginResponseDto> actual = loginService.findAll();
        assertEquals(response, actual);
    }

    @Test
    public void saveInDbTest() {
        loginRepository.saveAll(logins);
        verify(loginRepository, times(1)).saveAll(logins);
    }
}