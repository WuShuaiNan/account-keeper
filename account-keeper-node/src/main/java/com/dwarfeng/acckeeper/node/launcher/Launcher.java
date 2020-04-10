package com.dwarfeng.acckeeper.node.launcher;

import com.dwarfeng.springterminator.sdk.util.ApplicationUtil;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class Launcher {

    public static void main(String[] args) {
        ApplicationUtil.launch("classpath:spring/application-context*.xml");
    }
}
