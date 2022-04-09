package com.company.testtask.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class PostingResponseDto {
    private long matDoc;
    private long item;
    private LocalDate docDate;
    private LocalDate pstngDate;
    private String materialDescription;
    private long quantity;
    private String bUn;
    private BigDecimal amountLC;
    private String crcy;
    private String userName;
    private boolean authorizedDelivery;

}
