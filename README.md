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

## Execution Notes

Database:
* Install MySQL Community Edition
* Create a new schema, and execute the DDL found in the "data" folder
* TODO: Execute the CSV import script

Java Service:
* cd java-services
* mvn package
* Update YAML file with your DB connection info
* java -jar target/stockinfo-0.1.jar server stockinfo.yml
* Sample URL: http://localhost:8080/company?name=ABC

Ember Frontend
* bower install amcharts3
* cd ember-stockinfo
* ember server --proxy http://localhost:8080
	* or wherever your Java service has been deployed
* http://localhost:4200







## Installation

* `git clone <repository-url>` this repository
* change into the new directory
* `npm install`
* `bower install`

## Running / Development

* `ember server`
* Visit your app at [http://localhost:4200](http://localhost:4200).

### Code Generators

Make use of the many generators for code, try `ember help generate` for more details

### Running Tests

* `ember test`
* `ember test --server`

### Building

* `ember build` (development)
* `ember build --environment production` (production)

### Deploying

Specify what it takes to deploy your app.

## Further Reading / Useful Links

* [ember.js](http://emberjs.com/)
* [ember-cli](http://www.ember-cli.com/)
* Development Browser Extensions
  * [ember inspector for chrome](https://chrome.google.com/webstore/detail/ember-inspector/bmdblncegkenkacieihfhpjfppoconhi)
  * [ember inspector for firefox](https://addons.mozilla.org/en-US/firefox/addon/ember-inspector/)

