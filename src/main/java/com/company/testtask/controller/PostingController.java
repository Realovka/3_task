package com.company.testtask.controller;

import com.company.testtask.service.PostingService;
import com.company.testtask.service.dto.PostingDto;
import com.company.testtask.service.dto.PostingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/postings")
@RequiredArgsConstructor
public class PostingController {

    private final PostingService postingService;

    @GetMapping
    public List<PostingDto> findAll() {
        return postingService.findAll();
    }

    @PostMapping("/file")
    public void saveInFile() {
        postingService.saveInFile();
    }

    @PostMapping("/db")
    public void saveInDb() {
        postingService.saveInDb();
    }

    @GetMapping("/period")
    public List<PostingResponseDto> findByPeriod(@RequestParam("start") String dataStart,
                                                 @RequestParam("end")String dateEnd, @RequestParam("authorized") boolean authorized) {
        return postingService.findByPeriod(dataStart,dateEnd, authorized);
    }
}
