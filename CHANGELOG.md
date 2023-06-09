# ChangeLog

### Release_1.4.4_20220912_build_A

#### 功能构建

- 插件升级。
  - 升级 `maven-deploy-plugin` 插件版本为 `2.8.2`。

- 依赖升级。
  - 升级 `spring-terminator` 依赖版本为 `1.0.9.a`。
  - 升级 `dutil` 依赖版本为 `beta-0.3.1.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.2.10.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.4.a` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.4.9.a` 以规避漏洞。
  - 升级 `mysql` 依赖版本为 `8.0.28` 以规避漏洞。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.4.3_20220606_build_A

#### 功能构建

- 依赖升级。
  - 升级 `junit` 依赖版本为 `4.13.2` 以规避漏洞。
  - 升级 `spring` 依赖版本为 `5.3.20` 以规避漏洞。
  - 升级 `mysql` 依赖版本为 `8.0.27` 以规避漏洞。
  - 升级 `fastjson` 依赖版本为 `1.2.83` 以规避漏洞。
  - 升级 `netty` 依赖版本为 `4.1.77.Final` 以规避漏洞。
  - 升级 `hibernate-validator` 依赖版本为 `6.0.21.Final` 以规避漏洞。
  - 升级 `log4j2` 依赖版本为 `2.17.2` 以规避漏洞。
  - 升级 `slf4j` 依赖版本为 `1.7.36` 以规避漏洞。
  - 升级 `dutil` 依赖版本为 `beta-0.2.2.a` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.4.4.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.2.7.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.7.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.3.a` 以规避漏洞。

#### Bug修复

- (无)

#### 功能移除

- 删除不需要的依赖。
  - 删除 `joda-time` 依赖。
  - 删除 `pagehelper` 依赖。
  - 删除 `jsqlparser` 依赖。
  - 删除 `commons-fileupload` 依赖。

---

### Release_1.4.2_20220313_build_A

#### 功能构建

- 升级部分依赖的版本。
  - 升级 `log4j2` 版本至 `2.17.1`。
  - 升级 `hibernate` 版本至 `5.4.24.Final`。
  - 升级 `dubbo` 版本至 `2.7.15`。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.4.1_20211023_build_A

#### 功能构建

- 登录接口增加方法。
  - com.dwarfeng.acckeeper.stack.service.LoginService.getLoginState。

#### Bug修复

- 修正 `LoginServiceImpl` 中方法没有行为分析以及事务注解的 bug。

#### 功能移除

- (无)

---

### Release_1.4.0_20211022_build_A

#### 功能构建

- 账户、密码、登录相关接口完全重写。
  - com.dwarfeng.acckeeper.stack.service.AccountOperateService。
  - com.dwarfeng.acckeeper.stack.service.LoginService。

- 添加账户接口的预设查询。
  - com.dwarfeng.acckeeper.stack.service.AccountMaintainService.DISPLAY_NAME_LIKE。

- 移除项目中不需要的依赖。
  - `spring-web`。
  - `spring-mvc`。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.3.1_20211021_build_A

#### 功能构建

- 增加实体字段。
  - com.dwarfeng.acckeeper.stack.bean.entity.Account.displayName。

- 升级第三方依赖版本。
  - `hibernate-validator` 依赖版本升级至 `6.0.20.Final`。

- 去除无用的三方依赖。
  - httpclient。
  - httpmime。
  - httpcore。
  - solr-solrj。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.3.0_20201210_build_A

#### 功能构建

- 账户、密码、登录相关接口完全重写。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.2.1_20201209_build_A

#### 功能构建

- 优化 dubbo 的配置。
- 升级依赖版本。
  - druid 依赖版本升级至 1.1.20。
  - hibernate 依赖版本升级至 5.3.15.Final。
  - slf4j 依赖版本升级至 1.7.5。
  - subgrade 依赖版本升级至 1.2.0.b。
  - spring-terminator 依赖版本升级至 1.0.7.a。
  - spring-telqos 依赖版本升级至 1.1.1.a。
- 优化 redis 的配置。
- 消除预设配置文件中的真实的 ip 地址。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.2.0_20201018_build_A

#### 功能构建

- 将配置项目 hibernate 更名为 database。
- 更新多个依赖项目的版本。
  - 更新依赖 spring-terminator 版本至 1.0.6.a。
  - 更新依赖 subgrade 版本至 1.1.2.a。
  - 更新依赖 dubbo 版本至 2.7.6。
- 引入 spring-telqos 框架。
  - 新增指令 com.dwarfeng.acckeeper.impl.service.telqos.KickCommand。
  - 新增指令 com.dwarfeng.acckeeper.impl.service.telqos.StateCommand。
- 将 application-context-task.xml 中的参数设置为可配置参数。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.1.7_20200512_build_A

#### 功能构建

- 完善@Transactional注解的回滚机制。
- 更改项目的打包名称。
- 更新README.md。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.1.6_20200410_build_B

#### 功能构建

- (无)

#### Bug修复

- 禁止node模块运行（通常是在正式使用）时输出sql语句。

#### 功能移除

- (无)

---

### Release_1.1.6_20200410_build_A

#### 功能构建

- 优化程序的启动脚本。
- 将程序的启动方式整合至spring-terminator。

#### Bug修复

- 修复com.dwarfeng.acckeeper.api.integration包错误的拼写。
- 修复项目编译打包过程中发生的报警。

#### 功能移除

- (无)

---

### Release_1.1.5_20200222_build_A

#### 功能构建

- 将RegisterServiceImplTest中用于测试的账号名称从root改为foo，避免在测试的时候误删除已经存在的root账号。
- 升级subgrade项目版本为beta-0.2.4.a。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.1.4_20200218_build_B

#### 功能构建

- (无)

#### Bug修复

- 优化实体服务的预设查询样式。

#### 功能移除

- (无)

---

### Release_1.1.4_20200218_build_A

#### 功能构建

- 为FastJson相关的对象添加 of 静态方法。

#### Bug修复

- 升级subgrade项目版本为beta-0.2.3.a以修复PagedData对象中字段拼写错误bug。

#### 功能移除

- (无)

---

### Release_1.1.3_20200216_build_B

#### 功能构建

- (无)

#### Bug修复

- 升级subgrade项目版本为beta-0.2.2.b以修复查询对象的总页数错误bug。

#### 功能移除

- (无)

---

### Release_1.1.3_20200216_build_A

#### 功能构建

- 添加JS修复的实体对象。
- 实现AccountMaintainService查询所有实体的功能。
- 实现AccountMaintainService实体预设查询的功能。
- 升级subgrade项目版本为beta-0.2.2.a。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.1.2_20200215_build_B

#### 功能构建

- (无)

#### Bug修复

- 升级subgrade至beta-0.2.1.a以避免bug。

#### 功能移除

- (无)

---

### Release_1.1.2_20200215_build_A

#### 功能构建

- 修复ExceptionCodeOffsetConfiguration配置中的错误。

#### Bug修复

- (无)

#### 功能移除

- ~~去除允许在程序在没有默认账户的情况下建立默认账户的功能~~

---

### Release_1.1.1_20200215_build_A

#### 功能构建

- 解决代码粘贴的遗留问题，将实体以及方法的入口参数中的user替换为account。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.1.0_20200214_build_A

#### 功能构建

- 增加配置文件，允许在程序在没有默认账户的情况下建立默认账户。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.0.0_20200213_build_C

#### 功能构建

- 为dubbo框架配置dubbo.host属性。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.0.0_20200212_build_B

#### 功能构建

- 将工程中的文本“用户”替换为“账户”。
- 调整subgrade工程版本为beta-0.2.0.b。
- ExceptionCode偏移量可配置化。

#### Bug修复

- 修正ServiceExceptionCodes中的常量错误。

#### 功能移除

- (无)

---

### Release_1.0.0_20200212_build_A

#### 功能构建

- 工程全目标实现。

#### Bug修复

- (无)

#### 功能移除

- (无)
