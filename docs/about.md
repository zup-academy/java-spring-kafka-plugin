# **Java Spring Kafka Plugin**

Java Spring Kafka Plugin is a set of technologies and development methodology that together help build Kafka Publishers and Subscribers in Java Spring Boot Applications.

This Plugin has support for projects created with Stack Java Spring Boot REST API. And given that it also supports Java Spring Boot projects that use **Maven** as a dependency manager and have their property settings in the **YAML** pattern.


In the next sections you will find detailed information on how to use Spring Kafka Java Plugin to enable the ability to build clients for Apache Kafka in your projects.

Below is a summary of each section of this documentation.

1. [Plugin Core Technologies](#plugin-base-technologies)
2. [Capabilities Enabled when using the Plugin](#what-are-the-capabilities-enabled)
3. [Benefits of using the Plugin](#what-are-the-benefits-of-using-java-spring-kafka-plugin)
4. [Applying Java Spring Kafka Plugin](#applying-java-spring-kafka-plugin)


## **Plugin base technologies**

The purpose of this session is to inform which technologies are part of the Java Spring Kafka Plugin.

By applying this plugin to a Spring Boot Java project, your application will be able to benefit from the entire Spring for Apache Kafka tool infrastructure, which will allow you to extract the maximum from Apache Kafka.


### **Technological Composition**

The definition of this Plugin was thought aiming the biggest pains in the use of Apache Kafka clients in Java applications.

We understand that quality is non-negotiable, and we look to technologies and methodologies as a means to obtain the much-desired software quality. This premise was the guide for choosing each technology detailed below.


- Production environment
    - Spring for Kafka
- Development environment
    - Docker Compose
        - Apache Kafka
        - Apache Zookeper
        - KafkaDrop UI
- Test environment
    - JUnit
    - KafkaEmbeddedBroker
    - KafkaIntegrationTest
    - Awaitility



## **What are the capabilities Enabled**

By applying the Java Spring Kafka Plugin to your Java Spring Boot project, your project will be able to:

1. Create Kafka Producers
2. Create Kafka Consumers
3. Create Topics in Kafka
4. Configure specialized settings for Consumers and Producers
5. Create an automated integration test suite with KafkaEmbeddedBroker
6. Create Tests for Producers with the KafkaIntegrationTest Abstraction
7. Create Tests of an asynchronous nature for Consumers with Awaitility
8. Development environment set up next to Docker with Docker-compose.
9. Access KafkaDrop UI to manage your brokers and topics


## **What are the benefits of using Java Spring Kafka Plugin**

1. Ease of creating Kafka Clients in your project through the StackSpot CLI.
2. Custom Setup for Kafka Producers
3. Custom Setup for Kafka Consumers
4. KafkaIntegrationTest abstraction dedicated to help integration tests with KafkaEmbeddedBroker
5. Kafka Producer Creation example codes based on best practices.
5. Kafka Consumer Creation example codes based on best practices.
6. Integration Testing Sample Codes for Consumers based on best practices.
6. Sample Integration Testing Codes for Producers based on best practices.
7. Configuration of the test environment with JUnit and KafkaEmbeddedBroker.
8. DockerCompose for using Apache Kafka and Zookeeper, KafkaDrop in development environment.


[Watch this video to see the benefits of using Java Spring Kafka Plugin in your project](https://youtu.be/Gx2ejA3buEA)


## **Applying Java Spring Kafka Plugin**

To apply the Java Spring Kafka Plugin in your projects and enjoy its benefits, you must have the StackSpot CLI installed on your machine. [If not, follow this tutorial to install](https://docs.stackspot.com/docs/stk-cli/installation/).

### 1. Import the Stack on your machine

```sh
stk import stack https://github.com/zup-academy/java-springboot-restapi-stack
```

### 2. Now check if the Stack was successfully imported

```sh
stk list stack | grep java-springboot
```

### 3. Apply the Plugin, in your project directory, execute

```sh
stk apply plugin java-springboot-restapi-stack/java-spring-kafka-plugin
```

### 4. Check the changes in your project

```sh
git status
```



## Support

If you need help, please open an [issue in Stack's Github repository](https://github.com/zup-academy/java-spring-kafka-plugin/issues).