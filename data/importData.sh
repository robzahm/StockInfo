#!/bin/sh

# Validate that 2 parameters were passed in, and display them to the user
if [ $# -eq 2 ]
  then
    echo "Executing script as user: $1 with password: $2"
  else
  	echo "Usage: ./importData.sh username password"
  	echo "User must have 'create database' privileges"
  	exit
fi

# Create the database schema
/usr/local/mysql/bin/mysql --user $1 --password=$2 < ${PWD}/Schema.txt

# Create the company table
/usr/local/mysql/bin/mysql --user $1 --password=$2 stockinfo < ${PWD}/DDL.txt

# Load data into the company table
/usr/local/mysql/bin/mysqlimport --fields-terminated-by=, --fields-enclosed-by='"' --ignore-lines=1 --user $1 --password=$2 stockInfo ${PWD}/company.csv1 ${PWD}/company.csv2 ${PWD}/company.csv3