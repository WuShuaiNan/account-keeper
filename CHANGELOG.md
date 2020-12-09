# ChangeLog

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
