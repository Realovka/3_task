package com.company.testtask.service.impl;

import com.company.testtask.dao.entity.Posting;
import com.company.testtask.dao.repository.PostingRepository;
import com.company.testtask.service.PostingService;
import com.company.testtask.service.dto.LoginDto;
import com.company.testtask.service.dto.PostingDto;
import com.company.testtask.service.dto.PostingResponseDto;
import com.company.testtask.service.mapper.Mapper;
import com.company.testtask.service.mapper.MapperToResponseDto;
import com.company.testtask.service.reader.EntityReader;
import com.company.testtask.service.trimmer.Trimmer;
import com.company.testtask.service.writer.WriterToFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.company.testtask.service.util.DataUtil.TRUE;

@Service
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService {

    private static final String PATH_TO_POSTING_FILE = "src/main/resources/files/postings.csv";
    private static final String PATH_TO_LOGINS_FILE = "src/main/resources/files/logins.csv";

    private final PostingRepository postingRepository;
    private final EntityReader entityReader;
    private final Mapper<List<Posting>, List<PostingDto>> postingMapper;
    private final MapperToResponseDto<List<PostingResponseDto>, List<Posting>> postingAllFieldsMapper;
    private final MapperToResponseDto<List<PostingResponseDto>, List<PostingDto>> postingResponseDtoMapper;
    private final Trimmer<List<PostingDto>> trimmer;
    private final WriterToFile writerToFile;

    @Override
    public List<PostingResponseDto> findAll() {
        List<PostingDto> postingDtos = entityReader.createPostingDtos(PATH_TO_POSTING_FILE);
        return postingResponseDtoMapper.mapToResponseDto(postingDtos);
    }

    @Override
    public void saveInFile() {
        List<PostingDto> postingDtos = entityReader.createPostingDtos(PATH_TO_POSTING_FILE);
        addAuthorizedDelivery(postingDtos);
        writerToFile.writeToFile(postingDtos, PATH_TO_POSTING_FILE);
    }

    @Override
    public void saveInDb() {
        List<PostingDto> postingDtos = entityReader.createPostingDtos(PATH_TO_POSTING_FILE);
        postingDtos = trimmer.trimTabulationSymbol(postingDtos);
        List<Posting> postings = postingMapper.mapToEntity(postingDtos);
        postingRepository.saveAll(postings);
    }

    @Override
    public List<PostingResponseDto> findByPeriod(String dataStart, String dataEnd, boolean isAuthorized) {
        List<Posting> postings = postingRepository.findByPeriod(dataStart, dataEnd, isAuthorized);
        return postingAllFieldsMapper.mapToResponseDto(postings);
    }

    private void addAuthorizedDelivery(List<PostingDto> postingDtos) {
       List<LoginDto> loginDtos = entityReader.createLoginDtos(PATH_TO_LOGINS_FILE);
       postingDtos.forEach(postingDto -> postingDto.setAuthorizedDelivery(String.valueOf(loginDtos.stream().anyMatch(
               loginDto -> loginDto.getAppAccountName().equals(postingDto.getUserName()) && loginDto.getIsActive().equals(TRUE)))));
    }
}
