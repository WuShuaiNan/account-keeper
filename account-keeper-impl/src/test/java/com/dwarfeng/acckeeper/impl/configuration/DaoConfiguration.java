package com.dwarfeng.acckeeper.impl.configuration;

import com.dwarfeng.acckeeper.impl.bean.entity.HibernateAccount;
import com.dwarfeng.acckeeper.stack.bean.entity.Account;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    @Autowired
    private HibernateTemplate template;
    @Autowired
    private BeanTransformerConfiguration beanTransformerConfiguration;

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, Account, HibernateAccount> accountDaoDelegate() {
        return new HibernateBatchBaseDao<>(
                template,
                beanTransformerConfiguration.stringIdKeyBeanTransformer(),
                beanTransformerConfiguration.accountBeanTransformer(),
                HibernateAccount.class
        );
    }
}
