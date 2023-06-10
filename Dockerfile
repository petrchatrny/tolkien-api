FROM gradle:7.5.0-jdk18 AS build
WORKDIR /gradle

# build project
COPY . .
RUN gradle clean build -x test

FROM openjdk:17-jdk AS run
WORKDIR /app

COPY --from=build /gradle/build/libs/app.jar app.jar

ENV M_DB_DRIVER=""
ENV M_DB_URL=""
ENV M_DB_USERNAME=""
ENV M_DB_PASSWORD=""

EXPOSE 9999

ENTRYPOINT ["java", "-DDB_DRIVER=${M_DB_DRIVER}", "-DDB_URL=${M_DB_URL}", "-DDB_USERNAME=${M_DB_USERNAME}", "-DDB_PASSWORD=${M_DB_PASSWORD}", "-jar", "app.jar"]
