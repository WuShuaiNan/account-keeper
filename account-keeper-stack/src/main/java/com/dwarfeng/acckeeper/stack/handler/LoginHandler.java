package com.dwarfeng.acckeeper.stack.handler;

import com.dwarfeng.acckeeper.stack.bean.dto.LoginInfo;
import com.dwarfeng.acckeeper.stack.bean.entity.LoginState;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 登录处理器。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public interface LoginHandler extends Handler {

    /**
     * 登录。
     *
     * @param loginInfo 登录信息。
     * @return 登录状态。
     * @throws HandlerException 处理器异常。
     */
    LoginState login(LoginInfo loginInfo) throws HandlerException;

    /**
     * 登出。
     *
     * @param loginStateKey 指定的登录状态主键。
     * @throws HandlerException 处理器异常。
     */
    void logout(LongIdKey loginStateKey) throws HandlerException;

    /**
     * 判断指定的登录状态主键是否处于有效的登录状态。
     *
     * @param loginStateKey 指定的登录状态主键。
     * @return 指定的登录状态主键是否处于有效的登录状态。
     * @throws HandlerException 处理器异常。
     */
    boolean isLogin(LongIdKey loginStateKey) throws HandlerException;

    /**
     * 延长指定登录状态主键的超时日期。
     *
     * @param loginStateKey 指定的登录状态主键。
     * @return 延期后的新的登录状态。
     * @throws HandlerException 处理器异常。
     */
    LoginState postpone(LongIdKey loginStateKey) throws HandlerException;
}
