package com.medviv.auth.service.mapper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.medviv.auth.repository.entity.AccountEntity;
import com.medviv.auth.service.BaseSpringTest;
import com.medviv.model.Account;

public class AccountMapperTest extends BaseSpringTest {
    @Autowired
    private AccountMapper mapper;

    @Test
    public void testToEntity() {
        assertNull(mapper.toEntity(null));

        final Account a = new Account();
        a.setEmail("testing@gmail.com");
        a.setEnabled(true);

        final AccountEntity e = mapper.toEntity(a);

        assertEquals(a.getEmail(), e.getEmail());
        assertEquals(a.isEnabled(), e.isEnabled());
    }

    @Test
    public void testToDto() {
        assertNull(mapper.toDto(null));

        final AccountEntity e = new AccountEntity();
        e.setEmail("testing@gmail.com");
        e.setEnabled(false);

        final Account a = mapper.toDto(e);

        assertEquals(a.getEmail(), e.getEmail());
        assertEquals(a.isEnabled(), e.isEnabled());
    }
}