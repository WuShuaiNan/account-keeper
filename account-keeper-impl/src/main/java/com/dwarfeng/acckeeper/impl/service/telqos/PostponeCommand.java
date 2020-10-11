package com.dwarfeng.acckeeper.impl.service.telqos;

import com.dwarfeng.acckeeper.stack.bean.entity.LoginState;
import com.dwarfeng.acckeeper.stack.cache.LoginStateCache;
import com.dwarfeng.acckeeper.stack.service.LoginService;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostponeCommand extends CliCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostponeCommand.class);

    private static final String IDENTITY = "postpone";
    private static final String DESCRIPTION = "推迟用户登录的超时时间";
    private static final String CMD_LINE_SYNTAX_I = "postpone -i id";
    private static final String CMD_LINE_SYNTAX_N = "postpone -n name";
    private static final String CMD_LINE_SYNTAX_A = "postpone -a";
    private static final String CMD_LINE_SYNTAX = CMD_LINE_SYNTAX_I + System.lineSeparator() +
            CMD_LINE_SYNTAX_N + System.lineSeparator() + CMD_LINE_SYNTAX_A;

    public PostponeCommand() {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
    }

    @Autowired
    private LoginService loginService;
    @Autowired
    private LoginStateCache loginStateCache;

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        CommandUtils.buildInaOptions(list);
        return list;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    protected void executeWithCmd(Context context, CommandLine cmd) throws TelqosException {
        try {
            Pair<String, Integer> pair = CommandUtils.analyseInaCommand(cmd);
            if (pair.getRight() != 1) {
                context.sendMessage("下列选项必须且只能含有一个: -i -n -a");
                context.sendMessage(CMD_LINE_SYNTAX);
                return;
            }
            List<LoginState> loginStates = CommandUtils.getLoginStateFromInaCommand(loginStateCache, cmd);
            if (loginStates.isEmpty()) {
                context.sendMessage("not found.");
                return;
            }
            int successCount = 0;
            for (LoginState loginState : loginStates) {
                try {
                    loginService.postpone(loginState.getKey());
                    successCount++;
                } catch (Exception e) {
                    LOGGER.warn("ID " + loginState.getKey().getLongId() + " 延长登录时间失败，异常信息为", e);
                }
            }
            context.sendMessage(String.format("延长登录状态 %d/%d", successCount, loginStates.size()));
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }
}
