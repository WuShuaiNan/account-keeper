package com.dwarfeng.acckeeper.node.configuration;

import com.dwarfeng.acckeeper.stack.bean.entity.Account;
import com.dwarfeng.subgrade.impl.bean.key.ExceptionKeyFetcher;
import com.dwarfeng.subgrade.impl.service.DaoOnlyEntireLookupService;
import com.dwarfeng.subgrade.impl.service.GeneralCrudService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Autowired
    private CacheConfiguration cacheConfiguration;
    @Autowired
    private DaoConfiguration daoConfiguration;
    @Autowired
    private ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;
    @Value("${cache.timeout.entity.account}")
    private long accountTimeout;

    @Bean
    public GeneralCrudService<StringIdKey, Account> accountGeneralCrudService() {
        return new GeneralCrudService<>(
                daoConfiguration.accountHibernateBatchBaseDao(),
                cacheConfiguration.accountCacheDelegate(),
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                accountTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Account> daoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                daoConfiguration.accountHibernateEntireLookupDao(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }
}
