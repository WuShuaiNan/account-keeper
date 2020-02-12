package com.dwarfeng.acckeeper.impl.service;

import com.dwarfeng.acckeeper.stack.bean.entity.Account;
import com.dwarfeng.acckeeper.stack.bean.entity.LoginState;
import com.dwarfeng.acckeeper.stack.bean.entity.dto.RegisterInfo;
import com.dwarfeng.acckeeper.stack.service.AccountMaintainService;
import com.dwarfeng.acckeeper.stack.service.LoginService;
import com.dwarfeng.acckeeper.stack.service.RegisterService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Objects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class LoginServiceImplTest {

    @Autowired
    private AccountMaintainService accountMaintainService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private LoginService loginService;

    private RegisterInfo zhangSanReg;
    private RegisterInfo liSiReg;
    private RegisterInfo wangWuReg;

    @Before
    public void setUp() {
        zhangSanReg = new RegisterInfo("zhang_san", "张三", "ninja123456", "测试用账号");
        liSiReg = new RegisterInfo("li_si", "李四", "ninja123456", "测试用账号");
        wangWuReg = new RegisterInfo("wang_wu", "王五", "ninja123456", "测试用账号");
    }

    @After
    public void tearDown() {
        zhangSanReg = null;
        liSiReg = null;
        wangWuReg = null;
    }

    @Test
    public void test() throws Exception {
        Account zhangSan = null;
        Account liSi = null;
        Account wangWu = null;
        try {
            zhangSan = registerService.register(zhangSanReg);
            liSi = registerService.register(liSiReg);
            wangWu = registerService.register(wangWuReg);

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
        } finally {
            if (Objects.nonNull(zhangSan)) accountMaintainService.delete(zhangSan.getKey());
            if (Objects.nonNull(liSi)) accountMaintainService.delete(liSi.getKey());
            if (Objects.nonNull(wangWu)) accountMaintainService.delete(wangWu.getKey());
        }
    }
}