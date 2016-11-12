package com.medviv.auth.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.medviv.auth.service.BaseSpringTest;
import com.medviv.auth.service.mapper.AccountMapper;

public class AccountMapperTest extends BaseSpringTest {
    @Autowired
    private AccountMapper mapper;

    @Test
    public void testToEntity() {
        mapper.toEntity(null);
    }

    @Test
    public void testToDto() {
        mapper.toDto(null);
    }
}
