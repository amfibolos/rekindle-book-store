rootProject.name = "rekindle-book-store"
include("domain")
include("domain:domain-core")
findProject(":domain:domain-core")?.name = "domain-core"
include("infrastructure")
include("infrastructure:kafka")
findProject(":infrastructure:kafka")?.name = "kafka"
include("infrastructure:kafka:kafka-model")
findProject(":infrastructure:kafka:kafka-model")?.name = "kafka-model"
include("infrastructure:kafka:kafka-config")
findProject(":infrastructure:kafka:kafka-config")?.name = "kafka-config"
include("infrastructure:kafka:kafka-consumer")
findProject(":infrastructure:kafka:kafka-consumer")?.name = "kafka-consumer"
include("infrastructure:kafka:kafka-producer")
findProject(":infrastructure:kafka:kafka-producer")?.name = "kafka-producer"
include("infrastructure:authorization-server")
findProject(":infrastructure:authorization-server")?.name = "authorization-server"
include("infrastructure:eureka-server")
findProject(":infrastructure:eureka-server")?.name = "eureka-server"
include("infrastructure:gateway-server")
findProject(":infrastructure:gateway-server")?.name = "gateway-server"
include("infrastructure:config-server")
findProject(":infrastructure:config-server")?.name = "config-server"
include("order-service")
include("order-service:order-orm-adapter")
findProject(":order-service:order-orm-adapter")?.name = "order-orm-adapter"
include("order-service:order-messaging-adapter")
findProject(":order-service:order-messaging-adapter")?.name = "order-messaging-adapter"
include("order-service:order-rest-adapter")
findProject(":order-service:order-rest-adapter")?.name = "order-rest-adapter"
include("customer-service")
include("order-service:order-application-service")
findProject(":order-service:order-application-service")?.name = "order-application-service"
include("order-service:order-microservice")
findProject(":order-service:order-microservice")?.name = "order-microservice"
include("domain:domain-application")
findProject(":domain:domain-application")?.name = "domain-application"
include("payment-service")
include("payment-service:payment-application-service")
findProject(":payment-service:payment-application-service")?.name = "payment-application-service"
include("payment-service:payment-microservice")
findProject(":payment-service:payment-microservice")?.name = "payment-microservice"
include("payment-service:payment-orm-adapter")
findProject(":payment-service:payment-orm-adapter")?.name = "payment-orm-adapter"
include("payment-service:payment-rest-adapter")
findProject(":payment-service:payment-rest-adapter")?.name = "payment-rest-adapter"
include("payment-service:payment-messaging-adapter")
findProject(":payment-service:payment-messaging-adapter")?.name = "payment-messaging-adapter"
include("bookstore-service")
include("bookstore-service:bookstore-microservice")
findProject(":bookstore-service:bookstore-microservice")?.name = "bookstore-microservice"
include("bookstore-service:bookstore-orm-adapter")
findProject(":bookstore-service:bookstore-orm-adapter")?.name = "bookstore-orm-adapter"
include("bookstore-service:bookstore-rest-adapter")
findProject(":bookstore-service:bookstore-rest-adapter")?.name = "bookstore-rest-adapter"
include("bookstore-service:bookstore-application-service")
findProject(":bookstore-service:bookstore-application-service")?.name =
    "bookstore-application-service"
include("bookstore-service:bookstore-messaging-adapter")
findProject(":bookstore-service:bookstore-messaging-adapter")?.name = "bookstore-messaging-adapter"
include("domain:domain-bookstore")
findProject(":domain:domain-bookstore")?.name = "domain-bookstore"
include("domain:domain-order")
findProject(":domain:domain-order")?.name = "domain-order"
include("domain:domain-payment")
findProject(":domain:domain-payment")?.name = "domain-payment"
include("infrastructure:saga")
findProject(":infrastructure:saga")?.name = "saga"
include("infrastructure:outbox")
findProject(":infrastructure:outbox")?.name = "outbox"
