FROM gradle:8.8.0-jdk17

WORKDIR ./

COPY ./ .

RUN gradle installDist

CMD ./build/install/java-project-99/bin/java-project-99