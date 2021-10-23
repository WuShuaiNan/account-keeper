package com.dwarfeng.acckeeper.impl.service;

import com.dwarfeng.acckeeper.stack.bean.dto.LoginInfo;
import com.dwarfeng.acckeeper.stack.bean.entity.LoginState;
import com.dwarfeng.acckeeper.stack.handler.LoginHandler;
import com.dwarfeng.acckeeper.stack.service.LoginService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final LoginHandler loginHandler;

    private final ServiceExceptionMapper sem;

    public LoginServiceImpl(LoginHandler loginHandler, ServiceExceptionMapper sem) {
        this.loginHandler = loginHandler;
        this.sem = sem;
    }

    @Override
    public boolean isLogin(LongIdKey loginStateKey) throws ServiceException {
        try {
            return loginHandler.isLogin(loginStateKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("判断是否登录时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public LoginState getLoginState(LongIdKey loginStateKey) throws ServiceException {
        try {
            return loginHandler.getLoginState(loginStateKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("获取登录状态时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public LoginState login(LoginInfo loginInfo) throws ServiceException {
        try {
            return loginHandler.login(loginInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("登录时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void logout(LongIdKey loginStateKey) throws ServiceException {
        try {
            loginHandler.logout(loginStateKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("登出时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public LoginState postpone(LongIdKey loginStateKey) throws ServiceException {
        try {
            return loginHandler.postpone(loginStateKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("推迟超时日期时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
