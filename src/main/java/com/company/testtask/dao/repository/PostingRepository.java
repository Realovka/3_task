package com.company.testtask.dao.repository;

import com.company.testtask.dao.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {
    @Query(nativeQuery = true,value = "select * from postings where pstng_date >= ?1 and pstng_date < ?2 and authorized_delivery = ?3")
    List<Posting> findByPeriod(String dataStart, String dataEnd, boolean isAuthorized);
}
