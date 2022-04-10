package com.company.testtask.service.impl;

import com.company.testtask.dao.entity.Posting;
import com.company.testtask.dao.repository.PostingRepository;
import com.company.testtask.service.dto.PostingDto;
import com.company.testtask.service.dto.PostingResponseDto;
import com.company.testtask.service.mapper.Mapper;
import com.company.testtask.service.mapper.MapperToResponseDto;
import com.company.testtask.service.reader.EntityReader;
import com.company.testtask.service.trimmer.Trimmer;
import com.company.testtask.service.writer.WriterToFile;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

//@ExtendWith(MockitoExtension.class)
class Ex {
    @InjectMocks
    private PostingServiceImpl postingService;
    @Mock
    private PostingRepository postingRepository;
    @Mock
    private EntityReader entityReader;
    @Mock
    private Mapper<List<Posting>, List<PostingDto>> postingMapper;
    @Mock
    private MapperToResponseDto<List<PostingResponseDto>, List<Posting>> postingAllFieldsMapper;
    @Mock
    private MapperToResponseDto<List<PostingResponseDto>, List<PostingDto>> postingResponseDtoMapper;
    @Mock
    private Trimmer<List<PostingDto>> trimmer;
    @Mock
    private WriterToFile writerToFile;
    private String PATH_TO_POSTING_FILE;
    private String PATH_TO_LOGINS_FILE;
    private List<PostingDto> postingDtos;
    private List<PostingResponseDto> response;
    private List<Posting> postings;
    private List<PostingResponseDto> periodDtos;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        PATH_TO_POSTING_FILE = "src/main/resources/files/postings.csv";
        postingDtos = List.of(PostingDto.builder()
                .matDoc("6777727662")
                .item("\t2")
                .docDate("\t25.07.2020")
                .pstngDate("\t25.07.2020")
                .materialDescription("\tHeadphones JBL T110BT WHT white")
                .quantity("\t2")
                .bUn("\tpc")
                .amountLC("\t75")
                .crcy("\tBYN")
                .userName("\tNLIMONOV")
                .build());
        response = List.of(PostingResponseDto.builder()
                .matDoc(6777727662L)
                .build());
        postings = List.of(Posting.builder()
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
        periodDtos = List.of(PostingResponseDto.builder()
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
    public void findAllTest() { //fail
        when(entityReader.createPostingDtos(PATH_TO_POSTING_FILE)).thenReturn(postingDtos);
        when(postingResponseDtoMapper.mapToResponseDto(postingDtos)).thenReturn(response);
        List<PostingResponseDto> actual = postingService.findAll();
        Assert.assertEquals(response, actual);
    }

    @Test
    public void findByDayTest() {
        when(postingRepository.findByPeriod("25-07-2020", "26-07-2020", false)).thenReturn(postings);
        when(postingAllFieldsMapper.mapToResponseDto(postings)).thenReturn(periodDtos);
        List<PostingResponseDto> actual = postingService.findByPeriod("25-07-2020", "26-07-2020", false);
        Assert.assertEquals(periodDtos, actual);
    }

    @Test
    public void findByMonthTest() {
        when(postingRepository.findByPeriod("01-07-2020", "01-08-2020", false)).thenReturn(postings);
        when(postingAllFieldsMapper.mapToResponseDto(postings)).thenReturn(periodDtos);
        List<PostingResponseDto> actual = postingService.findByPeriod("01-07-2020", "01-08-2020", false);
        Assert.assertEquals(periodDtos, actual);
    }

    @Test
    public void findByYearTest() {
        when(postingRepository.findByPeriod("01-01-2020", "01-01-2021", false)).thenReturn(postings);
        when(postingAllFieldsMapper.mapToResponseDto(postings)).thenReturn(periodDtos);
        List<PostingResponseDto> actual = postingService.findByPeriod("01-01-2020", "01-01-2021", false);
        Assert.assertEquals(periodDtos, actual);
    }

}