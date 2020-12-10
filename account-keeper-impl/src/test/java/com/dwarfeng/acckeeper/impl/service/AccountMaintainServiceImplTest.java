package com.dwarfeng.acckeeper.impl.service;

import com.dwarfeng.acckeeper.stack.bean.entity.Account;
import com.dwarfeng.acckeeper.stack.bean.entity.dto.AccountInfo;
import com.dwarfeng.acckeeper.stack.service.AccountMaintainService;
import com.dwarfeng.acckeeper.stack.service.AccountService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class AccountMaintainServiceImplTest {

    @Autowired
    private AccountMaintainService accountMaintainService;
    @Autowired
    private AccountService accountService;

    private AccountInfo zhangSanReg;
    private AccountInfo liSiReg;
    private AccountInfo wangWuReg;

    @Before
    public void setUp() {
        zhangSanReg = new AccountInfo(new StringIdKey("zhang_san"), true, "测试用账号");
        liSiReg = new AccountInfo(new StringIdKey("li_si"), true, "测试用账号");
        wangWuReg = new AccountInfo(new StringIdKey("wang_wu"), true, "测试用账号");
    }

    @After
    public void tearDown() {
        zhangSanReg = null;
        liSiReg = null;
        wangWuReg = null;
    }

    @Test
    public void test() throws ServiceException {
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
            assertEquals(zhangSanReg.isEnabled(), zhangSan.isEnabled());
            assertEquals(liSiReg.isEnabled(), liSi.isEnabled());
            assertEquals(wangWuReg.isEnabled(), wangWu.isEnabled());
        } finally {
            accountMaintainService.deleteIfExists(zhangSanReg.getKey());
            accountMaintainService.deleteIfExists(liSiReg.getKey());
            accountMaintainService.deleteIfExists(wangWuReg.getKey());
        }
    }
}
