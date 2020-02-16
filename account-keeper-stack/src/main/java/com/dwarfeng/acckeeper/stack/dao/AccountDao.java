package com.dwarfeng.acckeeper.stack.dao;

import com.dwarfeng.acckeeper.stack.bean.entity.Account;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 账户数据访问层。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface AccountDao extends BatchBaseDao<StringIdKey, Account>, EntireLookupDao<Account>,
        PresetLookupDao<Account> {
}
