#
# Build stage
#
FROM eclipse-temurin:18-alpine AS build
WORKDIR /gradle

COPY .. .
RUN ./gradlew clean build -x test --no-daemon --parallel

#
# Run stage
#
FROM openjdk:18-jdk AS run
WORKDIR /app
EXPOSE 80

ENV PORT=""
ENV PROFILE=""
ENV DB_DRIVER=""
ENV DB_URL=""
ENV DB_USERNAME=""
ENV DB_PASSWORD=""

COPY --from=build /gradle/build/libs/app*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
