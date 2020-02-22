package com.dwarfeng.acckeeper.impl.service;

import com.dwarfeng.acckeeper.stack.bean.entity.dto.PasswordInfo;
import com.dwarfeng.acckeeper.stack.bean.entity.dto.RegisterInfo;
import com.dwarfeng.acckeeper.stack.service.AccountMaintainService;
import com.dwarfeng.acckeeper.stack.service.RegisterService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class RegisterServiceImplTest {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private AccountMaintainService accountMaintainService;

    @Test
    public void register() throws ServiceException {
        RegisterInfo registerInfo = new RegisterInfo(
                "foo",
                "ninja123456",
                "这是用于测试的人员信息..."
        );
        StringIdKey key = new StringIdKey(registerInfo.getId());

        try {
            if (accountMaintainService.exists(key)) {
                accountMaintainService.delete(key);
            }
            registerService.register(registerInfo);
            assertTrue(registerService.checkPassword("foo", "ninja123456"));
            assertFalse(registerService.checkPassword("foo", "123456"));
            registerService.updatePassword(new PasswordInfo(
                    "foo",
                    "ninja123456",
                    "qwerty123456"
            ));
            assertTrue(registerService.checkPassword("foo", "qwerty123456"));
            assertFalse(registerService.checkPassword("foo", "123456"));
        } finally {
            if (accountMaintainService.exists(key)) {
                accountMaintainService.delete(key);
            }
        }
    }
}