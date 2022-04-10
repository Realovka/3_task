package com.company.testtask.controller;

import com.company.testtask.service.PostingService;
import com.company.testtask.service.dto.PostingDto;
import com.company.testtask.service.dto.PostingResponseDto;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@JsonTest
class PostingControllerTest {

    private PostingController postingController;
    @Mock
    private PostingService postingService;

    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private List<PostingDto> response;
    private List<PostingResponseDto> postingsWithPeriod;

    @BeforeEach
    public void setUp() {
        postingController = new PostingController(postingService);
        mockMvc = MockMvcBuilders.standaloneSetup(postingController).build();
        response = List.of(PostingDto.builder().matDoc(5478624451715L).build());
        postingsWithPeriod = List.of(PostingResponseDto.builder()
                .id(1)
                .matDoc(6777727662L)
                .item(2)
                .docDate(LocalDate.of(2020, 7, 25))
                .pstngDate(LocalDate.of(2020, 7, 25))
                .materialDescription("tHeadphones JBL T110BT WHT white")
                .quantity(2)
                .bUn("pc")
                .amountLC(new BigDecimal(75))
                .crcy("BYN")
                .userName("NLIMONOV")
                .authorizedDelivery(false)
                .build());
    }

    @Test
    public void findAllTest() throws Exception {
        when(postingService.findAll()).thenReturn(response);
        mockMvc.perform(get("/api/v1/postings")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(response)))
                .andExpect(status().isOk())
                .andExpect( jsonPath("$",hasSize(1)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void saveInFileTest() throws Exception {
        mockMvc.perform(post("/api/v1/postings/file")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void saveInDbTest() throws Exception {
        mockMvc.perform(post("/api/v1/postings/db")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void findByPeriodTest() throws Exception {
        when(postingService.findByPeriod("2020-07-25", "2020-07-26", false)).thenReturn(postingsWithPeriod);
        mockMvc.perform(get("/api/v1/postings/period")
                        .param("start", "2020-07-25")
                        .param("end", "2020-07-26")
                        .param("authorized", "false")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(response)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andDo(MockMvcResultHandlers.print());
    }
}