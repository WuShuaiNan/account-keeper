package com.dwarfeng.acckeeper.impl.dao.preset;

import com.dwarfeng.acckeeper.stack.service.AccountMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 账户的CriteriaMaker。
 *
 * @author DwArFeng
 * @since 1.1.3
 */
@Component
public class AccountPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria criteria, String preset, Object[] objs) {
        if (AccountMaintainService.ID_LIKE.equals(preset)) {
            idLike(criteria, objs);
        } else {
            throw new IllegalArgumentException("无法识别的预设: " + preset);
        }
    }

    private void idLike(DetachedCriteria criteria, Object[] objs) {
        try {
            StringIdKey stringIdKey = (StringIdKey) objs[0];
            criteria.add(Restrictions.like("stringId", stringIdKey.getStringId(), MatchMode.ANYWHERE));
            criteria.addOrder(Order.asc("stringId"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }
}
