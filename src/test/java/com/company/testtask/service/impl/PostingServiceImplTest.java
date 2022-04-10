package com.company.testtask.service.impl;

import com.company.testtask.dao.entity.Posting;
import com.company.testtask.dao.repository.PostingRepository;
import com.company.testtask.service.dto.PostingDto;
import com.company.testtask.service.dto.PostingFromFileDto;
import com.company.testtask.service.dto.PostingResponseDto;
import com.company.testtask.service.mapper.Mapper;
import com.company.testtask.service.mapper.MapperToResponseDto;
import com.company.testtask.service.reader.EntityReader;
import com.company.testtask.service.trimmer.Trimmer;
import com.company.testtask.service.writer.WriterToFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostingServiceImplTest {

    private PostingServiceImpl postingService;
    @Mock
    private PostingRepository postingRepository;
    @Mock
    private EntityReader entityReader;
    @Mock
    private Mapper<List<Posting>, List<PostingFromFileDto>> postingMapper;
    @Mock
    private MapperToResponseDto<List<PostingResponseDto>, List<Posting>> postingAllFieldsMapper;
    @Mock
    private MapperToResponseDto<List<PostingDto>, List<PostingFromFileDto>> postingResponseDtoMapper;
    @Mock
    private Trimmer<List<PostingFromFileDto>> trimmer;
    @Mock
    private WriterToFile writerToFile;
    private String PATH_TO_POSTING_FILE;
    private List<PostingFromFileDto> postingFromFileDtos;
    private List<PostingDto> response;
    private List<Posting> postings;
    private List<PostingResponseDto> periodDtos;
    private List<Posting> emptyPostingList;
    private List<Posting> postingsToDb;
    private List<PostingResponseDto> emptyPeriodList;

    @BeforeEach
    void setUp() {
        postingService = new PostingServiceImpl(postingRepository, entityReader,
                postingMapper, postingAllFieldsMapper, postingResponseDtoMapper, trimmer, writerToFile);
        PATH_TO_POSTING_FILE = "src/main/resources/files/postings.csv";
        postingFromFileDtos = List.of(PostingFromFileDto.builder()
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
        response = List.of(PostingDto.builder()
                .matDoc(6777727662L)
                .build());
        postingsToDb = List.of(Posting.builder()
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
        emptyPeriodList = new ArrayList<>();
        emptyPostingList = new ArrayList<>();
    }

    @Test
    public void findAllTest() {
        when(entityReader.createPostingDtos(PATH_TO_POSTING_FILE)).thenReturn(postingFromFileDtos);
        when(postingResponseDtoMapper.mapToResponseDto(postingFromFileDtos)).thenReturn(response);
        List<PostingDto> actual = postingService.findAll();
        assertEquals(response, actual);
    }

    @Test
    public void saveInFileTest() {
        writerToFile.writeToFile(postingFromFileDtos, PATH_TO_POSTING_FILE);
        verify(writerToFile, times(1)).writeToFile(postingFromFileDtos, PATH_TO_POSTING_FILE);
    }

    @Test
    public void saveInDb() {
        postingRepository.saveAll(postingsToDb);
        verify(postingRepository, times(1)).saveAll(postingsToDb);
    }

    @Test
    public void findByDayTest() {
        when(postingRepository.findByPeriod("25-07-2020", "26-07-2020", false)).thenReturn(postings);
        when(postingAllFieldsMapper.mapToResponseDto(postings)).thenReturn(periodDtos);
        List<PostingResponseDto> actual = postingService.findByPeriod("25-07-2020", "26-07-2020", false);
        assertEquals(periodDtos, actual);
    }

    @Test
    public void findByMonthTest() {
        when(postingRepository.findByPeriod("01-07-2020", "01-08-2020", false)).thenReturn(postings);
        when(postingAllFieldsMapper.mapToResponseDto(postings)).thenReturn(periodDtos);
        List<PostingResponseDto> actual = postingService.findByPeriod("01-07-2020", "01-08-2020", false);
        assertEquals(periodDtos, actual);
    }

    @Test
    public void findByQuarterTest() {
        when(postingRepository.findByPeriod("01-07-2020", "01-10-2020", false)).thenReturn(postings);
        when(postingAllFieldsMapper.mapToResponseDto(postings)).thenReturn(periodDtos);
        List<PostingResponseDto> actual = postingService.findByPeriod("01-07-2020", "01-10-2020", false);
        assertEquals(periodDtos, actual);
    }

    @Test
    public void findByYearTest() {
        when(postingRepository.findByPeriod("01-01-2020", "01-01-2021", false)).thenReturn(postings);
        when(postingAllFieldsMapper.mapToResponseDto(postings)).thenReturn(periodDtos);
        List<PostingResponseDto> actual = postingService.findByPeriod("01-01-2020", "01-01-2021", false);
        assertEquals(periodDtos, actual);
    }

    @Test
    public void findByDayNoConcurrenceTest() {
        when(postingRepository.findByPeriod("02-07-2020", "03-07-2020", false)).thenReturn(emptyPostingList);
        when(postingAllFieldsMapper.mapToResponseDto(emptyPostingList)).thenReturn(emptyPeriodList);
        List<PostingResponseDto> actual = postingService.findByPeriod("02-07-2020", "03-07-2020", false);
        assertEquals(new ArrayList<>(), actual);
    }

    @Test
    public void findByMonthNoConcurrenceTest() {
        when(postingRepository.findByPeriod("01-07-2020", "01-08-2020", false)).thenReturn(emptyPostingList);
        when(postingAllFieldsMapper.mapToResponseDto(emptyPostingList)).thenReturn(emptyPeriodList);
        List<PostingResponseDto> actual = postingService.findByPeriod("01-07-2020", "01-08-2020", false);
        assertEquals(new ArrayList<>(), actual);
    }

    @Test
    public void findByQuarterNoConcurrenceTest() {
        when(postingRepository.findByPeriod("01-07-2020", "01-10-2020", false)).thenReturn(emptyPostingList);
        when(postingAllFieldsMapper.mapToResponseDto(emptyPostingList)).thenReturn(emptyPeriodList);
        List<PostingResponseDto> actual = postingService.findByPeriod("01-07-2020", "01-10-2020", false);
        assertEquals(new ArrayList<>(), actual);
    }

    @Test
    public void findByYearNoConcurrenceTest() {
        when(postingRepository.findByPeriod("01-01-2020", "01-01-2021", false)).thenReturn(emptyPostingList);
        when(postingAllFieldsMapper.mapToResponseDto(emptyPostingList)).thenReturn(emptyPeriodList);
        List<PostingResponseDto> actual = postingService.findByPeriod("01-01-2020", "01-01-2021", false);
        assertEquals(new ArrayList<>(), actual);
    }
}