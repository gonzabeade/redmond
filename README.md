# Generic Boot App

## Database Credentials 
`webapp/src/main/resources/application.properties`

In the file: 

```properties
# Postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/redmond_tx
spring.datasource.username=uname
spring.datasource.password=upass

# Kafka
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.topic=quickstart
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer

# Redis
spring.redis.host=localhost
spring.redis.port=6379
# spring.redis.password=your_password # If Redis requires authentication
```

## Run 
`export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64 && mvn clean install && java -jar /home/gonza/ITBA/8C/bd2/itba-bd2/webapp/target/webapp-1.0-SNAPSHOT.jar`

## Docker-compose 
`docker-compose up --remove-orphans`