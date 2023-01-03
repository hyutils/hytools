package com.chaojilaji.hyutils.db.repository.impl;

import com.chaojilaji.hyutils.db.repository.ProcessNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ProcessNodeRepositoryImpl implements ProcessNodeRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
}