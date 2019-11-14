# multi-stage builds

# Compile and build the project
FROM openjdk:12 AS builder
ARG VERSION
ARG JAR_PATH

VOLUME /tmp
WORKDIR /
ADD . .

RUN ./gradlew --stacktrace clean test build
RUN mv /$JAR_PATH /app.jar

# Set up an optimized productive environment for the application execution
FROM openjdk:12-alpine
LABEL maintainer "nicholas.checan@gmail.com"

WORKDIR /

# Copy only the built jar and nothing else
COPY --from=builder /app.jar /

ENV VERSION=$VERSION
ENV JAVA_OPTS=-Dspring.profiles.active=production

EXPOSE 8080

ENTRYPOINT ["sh","-c","java -jar -Dspring.profiles.active=production /app.jar"]