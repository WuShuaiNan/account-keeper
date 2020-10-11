package com.dwarfeng.acckeeper.impl.service.telqos;

import com.dwarfeng.acckeeper.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.acckeeper.stack.bean.entity.LoginState;
import com.dwarfeng.acckeeper.stack.service.LoginService;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class LoginCommand extends CliCommand {

    private static final String IDENTITY = "login";
    private static final String DESCRIPTION = "用户登录";
    private static final String CMD_LINE_SYNTAX = "login -u account -p password";

    public LoginCommand() {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
    }

    @Autowired
    private LoginService loginService;

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder("u").required().hasArg(true).desc("账号名").build());
        list.add(Option.builder("p").required().hasArg(true).desc("密码").build());
        return list;
    }

    @Override
    protected void executeWithCmd(Context context, CommandLine cmd) throws TelqosException {
        try {
            String account = cmd.getOptionValue("n");
            StringIdKey accountKey = new StringIdKey(account);
            String password = cmd.getOptionValue("p");
            try {
                LoginState loginState = loginService.login(accountKey, password);
                context.sendMessage("登录成功");
                StringBuilder stringBuilder = new StringBuilder();
                int lengthOfLine = CommandUtils.renderTitle(stringBuilder, 1, account.length());
                stringBuilder.append(System.lineSeparator());
                CommandUtils.renderSeparator(stringBuilder, lengthOfLine);
                stringBuilder.append(System.lineSeparator());
                CommandUtils.renderSingleLoginState(stringBuilder, loginState, 1, 1, account.length());
                context.sendMessage(stringBuilder.toString());
            } catch (ServiceException e) {
                if (Objects.equals(e.getCode().getCode(), ServiceExceptionCodes.WRONG_PASSWORD.getCode())) {
                    context.sendMessage("登录失败: 密码错误!");
                } else if (Objects.equals(e.getCode().getCode(), ServiceExceptionCodes.ACCOUNT_NOT_EXISTS.getCode())) {
                    context.sendMessage("登录失败: 账号不存在!");
                } else {
                    throw e;
                }
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }
}
