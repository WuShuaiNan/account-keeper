package com.dwarfeng.acckeeper.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.Service;

import java.util.List;

/**
 * LongID服务。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface LongIdService extends Service {

    /**
     * 生成下一个 Long ID。
     *
     * @return 下一个 Long ID。
     * @throws Exception 服务异常。
     */
    long nextLongId() throws Exception;

    /**
     * 生成下一个 LongIdKey。
     *
     * @return 下一个 LongIdKey。
     * @throws Exception 服务异常。
     */
    LongIdKey nextLongIdKey() throws Exception;

    /**
     * 生成下一组 Long ID。
     *
     * @param size 生成 ID 的数量。
     * @return 下一个LongID。
     * @throws Exception 服务异常。
     * @since 1.4.7
     */
    List<Long> nextLongId(int size) throws Exception;

    /**
     * 生成下一组 LongIdKey
     *
     * @param number 生成 ID 的数量。
     * @return 下一组 LongIdKey
     * @throws Exception 服务异常。
     * @since 1.4.7
     */
    List<LongIdKey> nextLongIdKey(int number) throws Exception;
}
