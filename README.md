# Generic Boot App

## Database Credentials 
`webapp/src/main/resources/application.properties`

In the file: 

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/redmond_tx
spring.datasource.username=uname
spring.datasource.password=upass
```

## Run 
`export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64 && mvn clean install && java -jar /home/gonza/ITBA/8C/bd2/itba-bd2/webapp/target/webapp-1.0-SNAPSHOT.jar`