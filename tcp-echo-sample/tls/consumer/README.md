# Spring-Boot TLS and Unsecure TCP QuickStart

This example demonstrates how you can use Apache Camel to expose a TCP TLS and unsecure port on OpenShift

### Notes
The unsecure TCP port will not be exposed on OpenShift online as LoadBalancers are not enabled.

### Building

The example can be built with

    mvn clean install

### Running the example in OpenShift

It is assumed that:
- OpenShift platform is already running, if not you can find details how to [Install OpenShift at your site](https://docs.openshift.com/container-platform/3.3/install_config/index.html).
- Your system is configured for Fabric8 Maven Workflow, if not you can find a [Get Started Guide](https://access.redhat.com/documentation/en/red-hat-jboss-middleware-for-openshift/3/single/red-hat-jboss-fuse-integration-services-20-for-openshift/)

The example can be built and run on OpenShift using a single goal:

    mvn fabric8:deploy

When the example runs in OpenShift, you can use the OpenShift client tool to inspect the status

To list all the running pods:

    oc get pods

Then find the name of the pod that runs this quickstart, and output the logs from the running pods with:

    oc logs <name of pod>

You can also use the OpenShift [web console](https://docs.openshift.com/container-platform/3.3/getting_started/developers_console.html#developers-console-video) to manage the
running pods, and view logs and much more.

### Accessing the TCP TLS route
1. Find the route to use ```oc get route echo $(oc get route/tls --template {{.spec.host}})```
1. You can either use a web browser and go to https://${host-above} or use a TLS TCP client to see the output.  See the client in the parent folder as a sample.

### Accessing the Unsecure TCP NodePort
This section requires knowing a node's ip address.  Find it from your cluster admin, this needs to be the public ip (external to the cluster interface).  You could use an external load balancer to expose this port and balance it across all of the computer nodes in your cluster.

1. Execute telnet $(ipFromAbove) 30556
1. Type something and press enter it will echo it back