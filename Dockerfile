FROM openjdk:11 AS base
WORKDIR /app

FROM base AS final
WORKDIR /app
COPY /build/libs/*SNAPSHOT.jar data.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:54363","-jar","data.jar"]
