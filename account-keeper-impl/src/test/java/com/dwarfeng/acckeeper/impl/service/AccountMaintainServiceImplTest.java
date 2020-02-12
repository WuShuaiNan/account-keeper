package com.dwarfeng.acckeeper.impl.service;

import com.dwarfeng.acckeeper.stack.bean.entity.Account;
import com.dwarfeng.acckeeper.stack.bean.entity.dto.RegisterInfo;
import com.dwarfeng.acckeeper.stack.service.AccountMaintainService;
import com.dwarfeng.acckeeper.stack.service.RegisterService;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Objects;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class AccountMaintainServiceImplTest {

    @Autowired
    private AccountMaintainService accountMaintainService;
    @Autowired
    private RegisterService registerService;

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
    public void test() throws ServiceException {
        Account zhangSan = null;
        Account liSi = null;
        Account wangWu = null;
        try {
            zhangSan = registerService.register(zhangSanReg);
            liSi = registerService.register(liSiReg);
            wangWu = registerService.register(wangWuReg);
        } finally {
            if (Objects.nonNull(zhangSan)) accountMaintainService.delete(zhangSan.getKey());
            if (Objects.nonNull(liSi)) accountMaintainService.delete(liSi.getKey());
            if (Objects.nonNull(wangWu)) accountMaintainService.delete(wangWu.getKey());
        }
    }
}