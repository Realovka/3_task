package com.company.testtask.controller;

import com.company.testtask.service.PostingService;
import com.company.testtask.service.dto.PostingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/postings")
@RequiredArgsConstructor
public class PostingController {

    private final PostingService postingService;

    @GetMapping
    public List<PostingResponseDto> findAll() {
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
