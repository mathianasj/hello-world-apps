# Prereq

## Jenkins setup
1. Setup a project named jenkins
1. Instantiate a jenkins in this new project (jenkins)

## Project setup and access setup
```
oc new-project sample-pipeline-dev
oc policy add-role-to-user edit system:serviceaccount:sample-pipeline:jenkins -n sample-pipeline-dev
oc policy add-role-to-user system:image-puller system:serviceaccount:sample-pipeline:default -n sample-pipeline-dev
oc new-project sample-pipeline-qa
oc policy add-role-to-user edit system:serviceaccount:sample-pipeline:jenkins -n sample-pipeline-qa
oc new-project sample-pipeline-prod
oc policy add-role-to-user edit system:serviceaccount:sample-pipeline:jenkins -n sample-pipeline-prod
```

## Jenkins service account external registry secret
```
oc create secret docker-registry docker-registry-secret \
--docker-server=<registry-server> \
--docker-username=<registry-username> \
--docker-password=<registry-password> \

oc secrets link default docker-registry-secret --for=pull,mount

oc secrets link builder docker-registry-secret
```

## Jenkins Setup
1. Login to the jenkins created in the jenkins project
1. Click on "New Item"
1. Enter a name
1. Select Pipeline
1. Click Ok
1. Under the definition drop down select "Pipeline script from SCM"
1. Select the SCM of Git
1. Enter the repository url ```https://github.com/mathianasj/hello-world-apps.git```
1. Update the script path to ```sample-pipeline/Jenkinsfile```
1. Click on "Build Now"