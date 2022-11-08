## ====================================================================================
## https://codefresh.io/docs/docs/learn-by-example/java/gradle/
## ====================================================================================
#FROM gradle:4.7.0-jdk8-alpine AS build
#COPY --chown=gradle:gradle . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle build --no-daemon
#
#FROM openjdk:8-jre-slim
#
#EXPOSE 8080
#
#RUN mkdir /app
#
#COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
## ====================================================================================



FROM openjdk:8-alpine as build
#FROM gradle:4.7.0-jdk8-alpine AS build
LABEL name = "my-new-knowledge--build"

WORKDIR ./root

COPY ./gradle .
COPY ./build.gradle.kts .
COPY ./gradle.properties .
COPY ./gradlew .
COPY ./gradlew.bat .
COPY ./settings.gradle.kts .

COPY ./src ./src

ENTRYPOINT ["java", "-jar", "dockerdemo.jar"]


# ===============
FROM openjdk:8-jdk-alpine
ENV SERVICE_NAME=any-service
ENV LOG_PATH=./docker/logs
RUN mkdir -p ${LOG_PATH}/${SERVICE_NAME}/ && ulimit -s 65536
RUN addgroup -S spring && adduser -S spring -G spring
RUN chown -R spring:spring ${LOG_PATH}/${SERVICE_NAME}/
USER spring:spring
ARG JAR_FILE=build/libs/${SERVICE_NAME}*.jar
COPY ${JAR_FILE} ${SERVICE_NAME}.jar

ENV JAVA_OPTS="-Xss1m -Xms128m -Xmx256m -Duser.timezone=UTC"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /$SERVICE_NAME.jar"]

