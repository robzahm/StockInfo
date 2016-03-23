# Ember-stockinfo

This README outlines the details of collaborating on this Ember application.
A short introduction of this app could easily go here.

## Prerequisites

You will need the following things properly installed on your computer.

* [Git](http://git-scm.com/)
* [Node.js](http://nodejs.org/) (with NPM)
* [Bower](http://bower.io/)
* [Ember CLI](http://www.ember-cli.com/)
* [PhantomJS](http://phantomjs.org/)
* [MySQL](http://dev.mysql.com/)
* [Maven](https://maven.apache.org/)

## Build and Installation Notes

* `git clone <repository-url>` this repository
* change into the new directory

Database:
* Install MySQL Community Edition
* change into the data directory
* Execute the importData.sh script in the data folder, passing in the username and password of an admin user with 'create database' privileges.  This will create the schema and table, and load it with data from the CSVs. e.g., './importData.sh root password'
* Note that MySQL needs read permissions to each folder level in order to read the file and import it, otherwise a permission error will be returned.

Java Service:
* change into the java-services directory
* 'mvn install'
* 'mvn package'
* Update YML file with your database connection info

Ember Frontend
* change into the ember-stockinfo directory
* 'npm install'
* 'bower install'

## Start the Services

Start the Java server:
* Start the Java server (DropWizard): 'java -jar target/stockinfo-0.1.jar server stockinfo.yml'
* Sample URL: http://localhost:8080/companies?name=App

Start the Ember server:
* 'ember server --proxy http://localhost:8080'

Access the application at [http://localhost:4200]http://localhost:4200.
