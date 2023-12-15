rootProject.name = "rekindle-book-store"
include("domain")
include("domain:domain-common")
findProject(":domain:domain-common")?.name = "domain-common"
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
include("order-service:order-messageing-adapter")
findProject(":order-service:order-messageing-adapter")?.name = "order-messageing-adapter"
include("order-service:order-rest-adapter")
findProject(":order-service:order-rest-adapter")?.name = "order-rest-adapter"
include("customer-service")
include("order-service:order-application-service")
findProject(":order-service:order-application-service")?.name = "order-application-service"
include("order-service:order-microservice")
findProject(":order-service:order-microservice")?.name = "order-microservice"
include("domain:domain-application")
findProject(":domain:domain-application")?.name = "domain-application"
