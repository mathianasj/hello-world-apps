# Spring Boot Example with Jenkins Pipeline

## Purpose

This project demonstrates a Java Spring Boot Application that includes a pipeline implementing the following flow.

1. Checkout SCM
1. Build spring boot embedded jar using maven
1. Execute JUnit tests using maven
1. Execute OpenShift build and tag as the latest imagestream
1. Deploy to the "dev" project
1. Request for approval to move to "qa" deployment
1. Deploy to the "qa" project
1. Request for approval to move to "prod" blue/green deployment
1. Deploy to non used prod environment
1. Allow approval before routing traffic to new blue/green "prod" environment

## Assumptions
1. These templates and source should NOT be used directly for production use

## Prerequestites
The guide below assumes you will create thre projects, which can be overriden during the environment variable section.

* jenkins - used to run the Jenkins instance
* spring-example-dev - used to run the dev instance of the sample app
* spring-example-qa - used to run the qa instance of the sample app
* spring-example-prod - used to host the prod blue/green environments of the sample app

This repo includes two OpenShift templates that can be used to setup the prod and non-prod OpenShift projects.  This will not create a project, only the contents that should exist in each project.  The files are located in the template folder.

* nonprod.yaml - the template to configure the non-production project contents
* prod.yaml - the template to configure the "production" project

## Openshift Setup and Building

### Environmennt Variables
These environment variables must be set in the context of your bash session.  They are using the assumed values described in the prerequesites section and can be overriden here.

```
export JENKINS_PROJ_NAME='jenkins'
export DEV_PROJ_NAME='spring-example-dev'
export QA_PROJ_NAME='spring-example-qa'
export PROD_PROJ_NAME='spring-example-prod'
```

### Jenkins Setup
Create the Jenkins project and app on OpenShift
```
oc new-project $JENKINS_PROJ_NAME
oc new-app --template="openshift/jenkins-ephemeral"
```
Find the url to access the newly create jenkins
```
echo http://`oc get route jenkins -o template --template={{.spec.host}}`
```

### Dev Project
