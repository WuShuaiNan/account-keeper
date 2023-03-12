@echo off

rem 程序的根目录
SET basedir="C:\Program Files\acckeeper"
rem 日志的根目录
SET logdir="%basedir%\logs"

rem 打开目录，执行程序
cd %basedir%
java -classpath "lib\*;libext\*" ^
-Xmx128 ^
-Dlog.dir=%logdir% ^
-Dlog.consoleEncoding=GBK ^
-Dlog.fileEncoding=UTF-8 ^
${mainClass}

@pause
