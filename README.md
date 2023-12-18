# <p align="center">  REKINDLE BOOK STORE

> <p align="center"> Welcome to the store which will reignite the flame of affection to your old school books and
> beyond...

## Built With

* [Java 21](https://github.com/corretto/corretto-21/releases)
* [Gradle 8.5](https://gradle.org/install/)
* [Spring Framework](https://spring.io/)
* [Kafka](https://kafka.apache.org/downloads)
* [Postres](https://www.postgresql.org/download/)

## Getting Started

### For Docker Users

* In the root project directory execute the following commands using either gradle or ./gradlew
* These will build source code and prepare docker images

><ul>
><li>gradle clean build</li>
><li>gradle jibDockerBuild</li>
></ul>

* Then go to ./docker-compose/rekindle-local directory
* and execute the following commands

><ul>
><li>docker-compose -f init_kafka_cluster.yml up -d</li>
* wait till all services have started
><li>docker-compose -f init_kafka_topics.yml up</li>
* this will add necessary kafka topics to the boostrap servers. Can be deleted afterwards
><li>docker-compose -f init_rekindle_app.yml up -d</li>
* this will start all microservices in sequence where some require kafka
</ul>


## Documentation
### Endpoints are documented using OpenApi
* http://localhost:8181/swagger-ui/index.html [order-microservice]
* http://localhost:8182/swagger-ui/index.html [payment-microservice]
* http://localhost:8183/swagger-ui/index.html [bookstore-microservice]
* http://localhost:8761/ [netflix-eureka service discovery]

### Oauth2 authorization
* Rekindle Network uses Oauth2 JTW authorization
><li>http://localhost:8023/oauth2/token [authorization server]</li>
><li>client id: internal-ms</li>
><li>client secret: Nr4lsn5o</li>
><li>scopes: communicate.read communicate.write</li>
><li>rant type: client credentials</li>
><li>header prefix: Bearer</li>

### Volume Mapping
* Kafka, zookeeper and postgres are mapped to folders inside the project for simplicity
><li>./docker-compose/rekindle-local/docker-compose/volumes/kafka/*</li>
><li>./docker-compose/rekindle-local/volumes/zookeeper/data/*</li>
><li>./docker-compose/rekindle-local/volumes/zookeeper/transactions/*</li>
><li>./docker-compose/rekindle-local/volumes/postgre/*</li>
* In case of docker container removals, contents of these folders also have to be deleted
* before creating the build anew
