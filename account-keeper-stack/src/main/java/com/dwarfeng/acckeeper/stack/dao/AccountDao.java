package com.dwarfeng.acckeeper.stack.dao;

import com.dwarfeng.acckeeper.stack.bean.entity.Account;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface AccountDao extends BatchBaseDao<StringIdKey, Account> {
}
