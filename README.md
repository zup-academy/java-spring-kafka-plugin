## Java Spring Kafka Plugin

The **java-spring-kafka-plugin** is a plugin to enable and configure Spring for Apache Kafka in Spring Boot Java application.

Applying this plugin into a Spring Boot project will prepare and configure it for all those features:

1. Enables and configures Spring Kafka to support working with Producers and Consumers on your project;
2. Configures the Kafka Consumer to work consistently. Favoring the features?
    - disabled auto-commit
    - Indicates that the reading of the topic must be by the initial offset, avoiding the loss of messages.
3. Enables the use of a kafka broker embedded in the integration tests of your Spring Boot application;
4. Generates production and test sample code so that you have a starting point for writing good production integration tests for Kafka producers and consumers;
5. Provides the `KafkaIntegrationTest` abstraction that simplifies writing integration tests for Kafka producer. Making it easier to listen to topic records.
6. Configure kafka environment with Docker Compose so you can run your app locally;

As you can see, the beauty of this plugin is that **you CAN build and validate kafka consumers and producers for locally** 🥳🥳


## How to use

The following steps show how to apply the plugin to an existing Java Spring Boot application.

1. First, import our stack if you haven't done it yet:
```sh
stk import stack https://github.com/zup-academy/java-springboot-restapi-stack
```

2. Now, in the project directory, apply the plugin and answer all the questions:
```sh
stk apply plugin java-springboot-restapi-stack/java-spring-kafka-plugin
```

3. Still inside the project directory, you can verify whether the plugin was applied or not by checking the updated and created files:
```sh
git status
```

Nice! You're ready for production I guess 🥳

[See here the benefits and how you can get the most out of the Java Spring Kafka Plugin](https://youtu.be/Gx2ejA3buEA)

## Support

If you need any help, please open an [issue on Plugin's Github repository](https://github.com/zup-academy/java-spring-kafka-plugin).