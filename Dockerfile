FROM eclipse-temurin:20-jdk

ARG GRADLE_VERSION=8.4

RUN apt-get update && apt-get install -yq make unzip

WORKDIR ./

COPY ./ .

RUN ./gradlew installDist

EXPOSE 8080

CMD ./build/install/java-project-99/bin/java-project-99