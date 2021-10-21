package com.dwarfeng.acckeeper.impl.service;

import com.dwarfeng.acckeeper.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.acckeeper.stack.bean.entity.Account;
import com.dwarfeng.acckeeper.stack.bean.entity.dto.AccountInfo;
import com.dwarfeng.acckeeper.stack.service.AccountMaintainService;
import com.dwarfeng.acckeeper.stack.service.AccountService;
import com.dwarfeng.acckeeper.stack.service.PasswordService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class PasswordServiceImplTest {

    @Autowired
    private AccountMaintainService accountMaintainService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordService passwordService;

    private AccountInfo zhangSanReg;
    private AccountInfo liSiReg;
    private AccountInfo wangWuReg;

    @Before
    public void setUp() {
        zhangSanReg = new AccountInfo(new StringIdKey("zhang_san"), true, "测试用账号", "张三");
        liSiReg = new AccountInfo(new StringIdKey("li_si"), false, "测试用账号", "李四");
        wangWuReg = new AccountInfo(new StringIdKey("wang_wu"), true, "测试用账号", "王五");
    }

    @After
    public void tearDown() {
        zhangSanReg = null;
        liSiReg = null;
        wangWuReg = null;
    }

    @Test
    public void test() throws Exception {
        Account zhangSan;
        Account liSi;
        Account wangWu;
        try {
            accountMaintainService.deleteIfExists(zhangSanReg.getKey());
            accountMaintainService.deleteIfExists(liSiReg.getKey());
            accountMaintainService.deleteIfExists(wangWuReg.getKey());

            accountService.register(zhangSanReg, "ninja123456");
            accountService.register(liSiReg, "ninja123456");
            accountService.register(wangWuReg, "ninja123456");
            zhangSan = accountMaintainService.get(zhangSanReg.getKey());
            liSi = accountMaintainService.get(liSiReg.getKey());
            wangWu = accountMaintainService.get(wangWuReg.getKey());

            assertTrue(passwordService.checkPassword(zhangSan.getKey(), "ninja123456"));
            assertFalse(passwordService.checkPassword(zhangSan.getKey(), "123456"));

            passwordService.updatePassword(liSi.getKey(), "ninja123456", "123456");
            assertFalse(passwordService.checkPassword(liSi.getKey(), "ninja123456"));
            assertTrue(passwordService.checkPassword(liSi.getKey(), "123456"));

            try {
                passwordService.updatePassword(liSi.getKey(), "ninja123456", "123456");
            } catch (ServiceException e) {
                assertEquals(ServiceExceptionCodes.WRONG_PASSWORD.getCode(), e.getCode().getCode());
            }

            passwordService.resetPassword(wangWu.getKey(), "123456");
            wangWu = accountMaintainService.get(wangWu.getKey());
            assertEquals(1, wangWu.getSerialVersion());
        } finally {
            accountMaintainService.deleteIfExists(zhangSanReg.getKey());
            accountMaintainService.deleteIfExists(liSiReg.getKey());
            accountMaintainService.deleteIfExists(wangWuReg.getKey());
        }
    }
}
