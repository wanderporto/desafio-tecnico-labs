FROM openjdk:11
ADD ./target/tracking-api-0.0.1-SNAPSHOT.jar /usr/src/tracking-api-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "tracking-api-0.0.1-SNAPSHOT.jar"]
