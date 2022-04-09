package com.company.testtask.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "postings")
@Entity
public class Posting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long matDoc;
    private long item;
    private LocalDate docDate;
    private LocalDate pstngDate;
    private String materialDescription;
    private long quantity;
    private String bUn;
    @Column(name = "amount_lc")
    private BigDecimal amountLC;
    private String crcy;
    private String userName;
    private boolean authorizedDelivery;


}
