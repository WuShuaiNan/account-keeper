# account-keeper

密码维护与登录验证的解决方案。

---

## 安装说明

1. 下载源码

   使用git进行源码下载。
   ```
   git clone git@gitee.com:wu_handsome_man/account-keeper.git
   ```
   对于中国用户，可以使用gitee进行高速下载。
   ```
   git clone git@github.com:WuShuaiNan/account-keeper.git
   ```
   
2. 项目打包

   进入项目根目录，执行maven命令
   ```
   mvn clean package
   ```
   
3. 解压

   找到打包后的目标文件 
   ```
   account-keeper-node/target/acckeeper-[version]-release.tar.gz
   ```
   将其解压至windows系统或者linux系统
   
4. 配置

   1. 进入工程下的`bin`目录，修改所有执行脚本的`basedir`和`logdir`
      
   2. 修改conf文件夹下的配置文件，着重修改各连接的url与密码。
   
5. enjoy it

---

## 分布式说明

该项目使用`dubbo`作为RPC框架，本身支持分布式，您可以在实际使用时，部署该项目任意数量，以进行分布式运算。

---
