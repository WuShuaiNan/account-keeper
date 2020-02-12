package com.dwarfeng.acckeeper.node.configuration;

import com.dwarfeng.acckeeper.impl.bean.entity.HibernateAccount;
import com.dwarfeng.acckeeper.stack.bean.entity.Account;
import com.dwarfeng.subgrade.impl.bean.DozerBeanTransformer;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.BeanTransformer;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanTransformerConfiguration {

    @Autowired
    private Mapper mapper;

    @Bean
    public BeanTransformer<LongIdKey, HibernateLongIdKey> longIdKeyBeanTransformer() {
        return new DozerBeanTransformer<>(
                LongIdKey.class,
                HibernateLongIdKey.class,
                mapper
        );
    }

    @Bean
    public BeanTransformer<StringIdKey, HibernateStringIdKey> stringIdKeyBeanTransformer() {
        return new DozerBeanTransformer<>(
                StringIdKey.class,
                HibernateStringIdKey.class,
                mapper
        );
    }

    @Bean
    public BeanTransformer<Account, HibernateAccount> accountBeanTransformer() {
        return new DozerBeanTransformer<>(
                Account.class,
                HibernateAccount.class,
                mapper
        );
    }
}
