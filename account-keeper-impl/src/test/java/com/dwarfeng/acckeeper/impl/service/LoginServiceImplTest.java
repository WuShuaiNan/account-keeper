package com.dwarfeng.acckeeper.impl.service;

import com.dwarfeng.acckeeper.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.acckeeper.stack.bean.entity.Account;
import com.dwarfeng.acckeeper.stack.bean.entity.LoginState;
import com.dwarfeng.acckeeper.stack.bean.entity.dto.AccountInfo;
import com.dwarfeng.acckeeper.stack.service.AccountMaintainService;
import com.dwarfeng.acckeeper.stack.service.AccountService;
import com.dwarfeng.acckeeper.stack.service.LoginService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
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
public class LoginServiceImplTest {

    @Autowired
    private AccountMaintainService accountMaintainService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private LoginService loginService;

    private AccountInfo zhangSanReg;
    private AccountInfo liSiReg;

    @Before
    public void setUp() {
        zhangSanReg = new AccountInfo(new StringIdKey("zhang_san"), true, "测试用账号", "张三");
        liSiReg = new AccountInfo(new StringIdKey("li_si"), false, "测试用账号", "李四");
    }

    @After
    public void tearDown() {
        zhangSanReg = null;
        liSiReg = null;
    }

    @Test
    public void test() throws Exception {
        Account zhangSan;
        Account liSi;
        try {
            accountMaintainService.deleteIfExists(zhangSanReg.getKey());
            accountMaintainService.deleteIfExists(liSiReg.getKey());

            accountService.register(zhangSanReg, "ninja123456");
            accountService.register(liSiReg, "ninja123456");
            zhangSan = accountMaintainService.get(zhangSanReg.getKey());
            liSi = accountMaintainService.get(liSiReg.getKey());

            LoginState loginState = loginService.login(zhangSan.getKey(), "ninja123456");
            loginState = loginService.postpone(loginState.getKey());
            try {
                loginService.login(zhangSan.getKey(), "ninja1234567");
            } catch (Exception e) {
                e.printStackTrace();
            }
            assertTrue(loginService.isLogin(loginState.getKey()));
            assertFalse(loginService.isLogin(new LongIdKey(loginState.getKey().getLongId() + 1)));
            loginService.logout(loginState.getKey());

            try {
                loginService.login(liSi.getKey(), "ninja123456");
            } catch (ServiceException e) {
                assertEquals(ServiceExceptionCodes.ACCOUNT_DISABLED.getCode(), e.getCode().getCode());
            }
        } finally {
            accountMaintainService.deleteIfExists(zhangSanReg.getKey());
            accountMaintainService.deleteIfExists(liSiReg.getKey());
        }
    }
}
