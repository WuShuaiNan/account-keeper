package com.dwarfeng.acckeeper.stack.service;

import com.dwarfeng.acckeeper.stack.bean.entity.Account;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.CrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;

/**
 * 账户维护服务。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface AccountMaintainService extends CrudService<StringIdKey, Account>, EntireLookupService<Account> {
}
