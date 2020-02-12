#!/bin/bash
# 程序的根目录
basedir=/usr/share/acckeeper

PID=$(cat $basedir/snowflake.pid)
kill "$PID"
