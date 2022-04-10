package com.company.testtask.service.impl;

import com.company.testtask.dao.entity.Posting;
import com.company.testtask.dao.repository.PostingRepository;
import com.company.testtask.service.PostingService;
import com.company.testtask.service.dto.LoginFromFileDto;
import com.company.testtask.service.dto.PostingDto;
import com.company.testtask.service.dto.PostingFromFileDto;
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
    private final Mapper<List<Posting>, List<PostingFromFileDto>> postingMapper;
    private final MapperToResponseDto<List<PostingResponseDto>, List<Posting>> postingAllFieldsMapper;
    private final MapperToResponseDto<List<PostingDto>, List<PostingFromFileDto>> postingResponseDtoMapper;
    private final Trimmer<List<PostingFromFileDto>> trimmer;
    private final WriterToFile writerToFile;

    @Override
    public List<PostingDto> findAll() {
        List<PostingFromFileDto> postingFromFileDtos = entityReader.createPostingDtos(PATH_TO_POSTING_FILE);
        return postingResponseDtoMapper.mapToResponseDto(postingFromFileDtos);
    }

    @Override
    public void saveInFile() {
        List<PostingFromFileDto> postingFromFileDtos = entityReader.createPostingDtos(PATH_TO_POSTING_FILE);
        addAuthorizedDelivery(postingFromFileDtos);
        writerToFile.writeToFile(postingFromFileDtos, PATH_TO_POSTING_FILE);
    }

    @Override
    public void saveInDb() {
        List<PostingFromFileDto> postingFromFileDtos = entityReader.createPostingDtos(PATH_TO_POSTING_FILE);
        postingFromFileDtos = trimmer.trimTabulationSymbol(postingFromFileDtos);
        List<Posting> postings = postingMapper.mapToEntity(postingFromFileDtos);
        postingRepository.saveAll(postings);
    }

    @Override
    public List<PostingResponseDto> findByPeriod(String dataStart, String dataEnd, boolean isAuthorized) {
        List<Posting> postings = postingRepository.findByPeriod(dataStart, dataEnd, isAuthorized);
        return postingAllFieldsMapper.mapToResponseDto(postings);
    }

    private void addAuthorizedDelivery(List<PostingFromFileDto> postingFromFileDtos) {
       List<LoginFromFileDto> loginFromFileDtos = entityReader.createLoginDtos(PATH_TO_LOGINS_FILE);
       postingFromFileDtos.forEach(postingDto -> postingDto.setAuthorizedDelivery(String.valueOf(loginFromFileDtos.stream().anyMatch(
               loginDto -> loginDto.getAppAccountName().equals(postingDto.getUserName()) && loginDto.getIsActive().equals(TRUE)))));
    }
}
