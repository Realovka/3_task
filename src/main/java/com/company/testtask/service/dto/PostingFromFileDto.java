package com.company.testtask.service.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostingFromFileDto {
    @CsvBindByPosition(position = 0)
    private String matDoc;
    @CsvBindByPosition(position = 1)
    private String item;
    @CsvBindByPosition(position = 2)
    private String docDate;
    @CsvBindByPosition(position = 3)
    private String pstngDate;
    @CsvBindByPosition(position = 4)
    private String materialDescription;
    @CsvBindByPosition(position = 5)
    private String quantity;
    @CsvBindByPosition(position = 6)
    private String bUn;
    @CsvBindByPosition(position = 7)
    private String amountLC;
    @CsvBindByPosition(position = 8)
    private String crcy;
    @CsvBindByPosition(position = 9)
    private String userName;
    private String authorizedDelivery;
}
