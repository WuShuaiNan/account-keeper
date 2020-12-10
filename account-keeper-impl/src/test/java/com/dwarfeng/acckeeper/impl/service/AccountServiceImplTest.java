package com.dwarfeng.acckeeper.impl.service;

import com.dwarfeng.acckeeper.stack.bean.entity.Account;
import com.dwarfeng.acckeeper.stack.bean.entity.dto.AccountInfo;
import com.dwarfeng.acckeeper.stack.service.AccountMaintainService;
import com.dwarfeng.acckeeper.stack.service.AccountService;
import com.dwarfeng.acckeeper.stack.service.PasswordService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private AccountMaintainService accountMaintainService;

    @Test
    public void register() throws ServiceException {
        AccountInfo accountInfo = new AccountInfo(
                new StringIdKey("foo"),
                true,
                "这是用于测试的人员信息..."
        );
        StringIdKey accountKey = accountInfo.getKey();
        try {
            if (accountMaintainService.exists(accountKey)) {
                accountMaintainService.delete(accountKey);
            }
            accountService.register(accountInfo, "ninja123456");
            assertTrue(passwordService.checkPassword(accountKey, "ninja123456"));
            assertFalse(passwordService.checkPassword(accountKey, "123456"));
            accountService.invalid(accountKey);
            Account account = accountMaintainService.get(accountKey);
            assertEquals(1, account.getSerialVersion());
            accountInfo.setEnabled(false);
            accountService.update(accountInfo);
            account = accountMaintainService.get(accountKey);
            assertEquals(2, account.getSerialVersion());
            assertFalse(account.isEnabled());
        } finally {
            if (accountMaintainService.exists(accountKey)) {
                accountMaintainService.delete(accountKey);
            }
        }
    }
}
