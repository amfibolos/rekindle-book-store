# <p align="center"> <span style="color:red;"> REKINDLE BOOK STORE

## <p align="center"> <span style="color:red;">Straight from the eighties with a synthwave vibe

<p align="center">
  <img width="460" src="content/miami.png" /><br /><br />
</p>

> <p align="center"> <span style="color:orange;"> Welcome to the store which will reignite the flame of affection to your old school books and
> beyond...

## <span style="color:green;"> Built With

* [Java 21](https://www.oracle.com/java/technologies/)
* [Gradle 8.5](https://gradle.org/)
* [Spring Framework](https://spring.io/)
* [Kafka](https://kafka.apache.org/)
* [PostgreSQL](https://www.postgresql.org/)

# <span style="color:yellow;"> Getting Started

## <span style="color:magenta;"> Docker

### <span style="color:magenta;"> 1. Docker Hub images download
* <span style="color:cyan;">In the root project directory execute the following command
```
./docker-compose-hub.sh
```

### <span style="color:magenta;"> 2. Local Docker images setup
* <span style="color:cyan;">In the root project directory execute the following command
```
./docker-compose-local.sh
```

* <span style="color:cyan;">Whichever you choose it will either
pull imgaes from https://hub.docker.com/u/damian0malecki or build them locally
* The infrastructure will include
```
- zookeeper
- 3 kafka brokers
- schema registry
- kafka manager
- postgreSQL
- Database one-time seeding service
- Kafka topics one-time seeding service
- Eureka server
- Config server
- Authorization server
- Gateway server
- Order service
- Bookstore service
- Payment service
- Customer service
```

### <span style="color:magenta;"> THAT'S IT. You're good to go :)

## <span style="color:gold;"> Documentation

### <span style="color:violet;"> Microservices

* <span style="color:orange;">Rekindle BookStore consists of several central services:

> customer-service - handles customer creation
>
> bookstore-service - handles bookstores in the rekindle network and their inventory
>
> order-service - orchestrates customer's purchases in rekindle network
>
> payment-service - handles customer's payments and wallet top-ups

### <span style="color:violet;"> Infrastructure

* <span style="color:orange;">Rekindle Bookstore network is handled internally by:

> config-server [handles microservices configuration locally]
>
> authorization-server [for OAuth2 authorization]
>
> eureka-discovery-server [for internal microservice discovery]
>
> gateway-server [for inbound traffic and load balancing]

### <span style="color:violet;"> Infrastructure - events

* <span style="color:orange;">Orders made in rekindle network are handled seamlessly via events
  orchestrated by Apache Kafka

> zookeeper
>
> 3 kafka brokers
>
> schema registry (with avro schema)
>
> kafka manager [available at http://localhost:9000]
>
> [kafka manager requires manual cluster configuration
> 1. go to cluster dropdown and add new cluster
> 2. Specify its name
> 3. In cluster host type zookeeper:2181
> 4. Now you can see available topics and brokers
     > ]

### <span style="color:violet;"> Infrastructure - database

* <span style="color:orange;">At the moment there is one central database but more will be
  introduced for each microservice
* <span style="color:orange;">To connect to the PostgreSQL Database please install the latest
  version

><li>connection url: jdbc:postgresql://localhost:5432/postgres</li>
><li>username: postgres</li>
><li>password: admin</li>
><li>schemas: bookstore,customer,order,payment</li>

### <span style="color:violet;"> Endpoints are documented using OpenApi

* http://localhost:8181/swagger-ui/index.html [order-microservice]
* http://localhost:8182/swagger-ui/index.html [payment-microservice]
* http://localhost:8183/swagger-ui/index.html [bookstore-microservice]
* http://localhost:8184/swagger-ui/index.html [customer-microservice]
* http://localhost:8761/ [netflix-eureka service discovery]

### <span style="color:violet;"> Gateway Server

* <span style="color:orange;">core url address of rekindle book store is http://localhost:8024
* <span style="color:orange;">the server is secured with JWT Oauth2 protocol
* <span style="color:orange;">available services in the network are:

><li>http://localhost:8024/rekindle/orders</li>
><li>http://localhost:8024/rekindle/payments</li>
><li>http://localhost:8024/rekindle/bookstores</li>
><li>http://localhost:8024/rekindle/customers</li>

### <span style="color:violet;"> Oauth 2.0 authorization

* <span style="color:orange;">Rekindle Network uses Oauth2 JTW authorization

><li>http://localhost:8023/oauth2/token [authorization server]</li>
><li>client id: internal-ms</li>
><li>client secret: Nr4lsn5o</li>
><li>scopes: communicate.read communicate.write</li>
><li>grant type: client credentials</li>
><li>header prefix: Bearer</li>

### <span style="color:violet;"> Docker Volume Mapping

* <span style="color:orange;">Kafka, zookeeper and postgres are mapped to folders inside the project
  for simplicity

><li>./docker-compose/rekindle-local/docker-compose/volumes/kafka/*</li>
><li>./docker-compose/rekindle-local/volumes/zookeeper/data/*</li>
><li>./docker-compose/rekindle-local/volumes/zookeeper/transactions/*</li>
><li>./docker-compose/rekindle-local/volumes/postgre/*</li>

* <span style="color:orange;">In case of docker container removals, contents of these folders also
  have to be deleted
  manually before creating the build anew. Alternatively there is a script which does all that
><li>./remove-volume-mapping.sh</li>

# <span style="color:cyan;">STILL UNDER CONSTRUCTION

### User Interface

* User interface will be created as a Single Page Application with React, NodeJs and Typescript

## License

Apache 2.0 © 