package com.company.testtask.dao.repository;

import com.company.testtask.dao.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
}
