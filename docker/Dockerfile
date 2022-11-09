FROM openjdk:8-jre-slim

EXPOSE 8080

RUN mkdir /app

COPY ./build/libs/my-new-knowledge-docker-*.jar ./app/my-new-knowledge.jar

ENTRYPOINT ["java", "-jar","/app/my-new-knowledge.jar"]
