package com.company.testtask.controller;

import com.company.testtask.service.LoginService;
import com.company.testtask.service.dto.LoginResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@JsonTest
class LoginControllerTest {

    private LoginController loginController;
    @Mock
    private LoginService loginService;
    private List<LoginResponseDto> response;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        loginController = new LoginController(loginService);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
        response = List.of(LoginResponseDto.builder()
                .application("SAP")
                .appAccountName("BORODZUTPNI")
                .isActive(true)
                .jobTitle("Комплектовщик товаров")
                .department("Группа складского хозяйства")
                .build());
    }

    @Test
    public void findAllTest() throws Exception {
        when(loginService.findAll()).thenReturn(response);
        mockMvc.perform(get("/api/v1/logins")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(response)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].application", equalTo("SAP")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void saveInDbTest() throws Exception {
        mockMvc.perform(post("/api/v1/logins")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}