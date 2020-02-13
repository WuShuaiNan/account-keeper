#!/bin/bash
# 程序的根目录
basedir=/usr/share/acckeeper

PID=$(cat $basedir/acckeeper.pid)
kill "$PID"
