## Service Account Roles
### On each project that will be managed by jenkins
oc policy add-role-to-user edit system:serviceaccount:jenkins:jenkins -n spring-example-ocp-dev
oc policy add-role-to-user edit system:serviceaccount:jenkins:jenkins -n spring-example-ocp-qa
oc policy add-role-to-user edit system:serviceaccount:jenkins:jenkins -n spring-example-ocp-prod

### Service account roles to allow access to dev image from another project
oc policy add-role-to-user system:image-puller system:serviceaccount:spring-example-ocp-qa:default --namespace=spring-example-ocp-dev
oc policy add-role-to-user system:image-puller system:serviceaccount:spring-example-ocp-qa:default --namespace=spring-example-ocp-prod