# **Java Spring Kafka Plugin**

Plugin Java Spring Kafka é um conjunto de técnologias e metodologia de desenvolvimento que juntos auxiliam na construção de Publishers e Subscribes Kafka em Aplicações Java Spring Boot. 

Este Plugin possui suporte para projetos criados junto a Stack Java Spring Boot REST API. E Dado a isso também suporta projetos Java Spring Boot que utilizem **Maven** como gerenciador de dependencias e tenham suas configurações de properties no padrão **YAML**.


Nas proximas sessões você encontrará em detalhes informações sobre como utilizar Plugin Java Spring Kafka para habilitar a capacidade de construir clients para Apache Kafka em seus projetos. 

Abaixo esta de forma sumariazada cada sessão desta documentação.

1. [Técnologias base da Plugin](#tecnologias-base-da-plugin)
2. [Capacidades Habilitadas ao uso da Plugin](#quais-são-as-capacidades-habilitadas)
3. [Beneficio de utilizar a Plugin](#quais-os-beneficios-de-utilizar-java-spring-kafka-plugin)
4. [Aplicando Java Spring Kafka Plugin](#aplicando-java-spring-kafka-plugin)


## **Tecnologias base da Plugin**

Objetivo desta sessão é informar quais são as técnologias que fazem parte do Java Spring Kafka Plugin.

Ao aplicar este plugin em um projeto Java Spring Boot, sua aplicação poderá se beneficiar de toda infraestrutura da ferramenta Spring for Apache Kafka, que lhe permitirá extrair o maximo do Apache Kafka.


### **Composição Técnologica**

A definição deste Plugin foi pensada visando as maiores dores no uso de clients Apache Kafka em aplicativos Java.

Entendemos que a qualidade é inegociavel, e olhamos para as técnologias e metodologias como meio para obter a tão desejada qualidade no software. Essa premissa foi o guia para escolha de cada técnologia detalhada abaixo.


- Ambiente de produção
    - Spring for Kafka
- Ambiente de desenvolvimento
    - Docker Compose
        - Apache Kafka
        - Apache Zookeper
        - KafkaDrop UI
- Ambiente de testes
    - JUnit
    - KafkaEmbeddedBroker
    - KafkaIntegrationTest
    - Awaitility



## **Quais são as capacidades Habilitadas**

Ao aplicar em seu projeto Java Spring Boot, o Java Spring Kafka Plugin, seu projeto será capaz:

1. Criar Produtores Kafka
2. Criar Consumidores Kafka
3. Criar Topicos em Kafka
4. Definir configurações especializadas para Consumidores e Produtores
5. Criar uma suite de testes de integração automatizada junto a KafkaEmbeddedBroker 
6. Criar Testes Para Produtores com a abstração KafkaIntegrationTest
7. Criar Testes de natureza assicrona para Consumidores com Awaitility 
8. Ambiente de desenvolvimento configurado junto ao Docker com Docker-compose.
9. Acessa KafkaDrop UI para gerenciar seus brokers e topicos


## **Quais os Beneficios de Utilizar Java Spring Kafka Plugin**

1. Facilidade na criação de Clients Kafka em seu projeto através da StackSpot CLI.
2. Configuração personalizada para Produtores Kafka
3. Configuração personalizada para Consumidores Kafka
4. KafkaIntegrationTest abstração dedicada a auxiliar testes de integração com KafkaEmbeddedBroker
5. Codigos de exemplo de Criação de Produtores Kafka baseado em boas praticas.
5. Codigos de exemplo de Criação de Consumidores Kafka baseado em boas praticas.
6. Codigos de exemplo de Testes de integração para Consumidores baseado em boas praticas.
6. Codigos de exemplo de Testes de integração para Produtores baseado em boas praticas.
7. Configuração do ambiente de testes com JUnit e KafkaEmbeddedBroker.
8. DockerCompose para uso do Apache Kafka e Zookeeper, KafkaDrop  em ambiente de desenvolvimento.


[Assita este video para ver os beneficios de utilizar Java Spring Kafka Plugin em seu projeto](https://youtu.be/Gx2ejA3buEA)


## **Aplicando Java Spring Kafka Plugin**

Para Aplicar o Java Spring Kafka Plugin em  seus projetos e desfrutar de seus beneficios é necessário que você tenha a CLI da StackSpot instalada em sua maquina. [Caso você não tenha siga este tutorial para fazer a instalação](https://docs.stackspot.com/docs/stk-cli/installation/).

### 1. Importe a Stack em sua maquina

```sh
stk import stack https://github.com/zup-academy/java-springboot-restapi-stack
```

### 2. Agora verifique se a Stack foi importada com sucesso

```sh
stk list stack | grep java-springboot
```

### 3. Aplique o Plugin, no diretorio do seu projeto, execute

```sh
stk apply plugin java-springboot-restapi-stack/java-spring-kafka-plugin
```   

### 4. Verifque as modificações em seu projeto

```sh
git status
```   



## Suporte

Caso precise de ajuda, por favor abra uma [issue no repositorio do Github da Stack](https://github.com/zup-academy/java-spring-kafka-plugin/issues).