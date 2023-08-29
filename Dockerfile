#
# Build stage
#
FROM gradle:7.5.0-jdk18 AS build
WORKDIR /gradle

COPY . .
RUN gradle clean build -x test

#
# Run stage
#
FROM openjdk:18-jdk AS run
WORKDIR /app
EXPOSE 80

ENV DB_DRIVER=""
ENV DB_URL=""
ENV DB_USERNAME=""
ENV DB_PASSWORD=""

COPY --from=build /gradle/build/libs/app.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
