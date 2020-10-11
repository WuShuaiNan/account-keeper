package com.dwarfeng.acckeeper.stack.cache;

import com.dwarfeng.acckeeper.stack.bean.entity.LoginState;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;
import com.dwarfeng.subgrade.stack.exception.CacheException;

import java.util.List;

/**
 * 登录状态缓存。
 *
 * @author DwArFeng
 * @since 0.1.0-alpha
 */
public interface LoginStateCache extends BatchBaseCache<LongIdKey, LoginState> {

    /**
     * 获取缓存中所有的登录状态。
     *
     * @return 所有的登录状态组成的列表。
     * @throws CacheException 缓存异常。
     * @since 1.2.0
     */
    List<LoginState> all() throws CacheException;
}
