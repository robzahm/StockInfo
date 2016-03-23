#!/bin/sh

/usr/local/mysql/bin/mysqlimport --fields-terminated-by=, --fields-enclosed-by='"' --ignore-lines=1 --user root --password stockInfo ${PWD}/company.csv1 ${PWD}/company.csv2 ${PWD}/company.csv3