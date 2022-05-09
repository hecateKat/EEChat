FROM maven:3.8-openjdk-17 as builder

RUN mkdir /app
WORKDIR /app

COPY ../../Desktop/dockery .

RUN mvn clear packaga #czy tam inna komenda do builda

#teraz musimy wiedzieć gdzie jest nas plik jar
RUN JAR_NAME=<tutaj podaj ścieżkę gdzie jest "target"> \
	&& mkdir /output \
	&& mv $JAR_PATH /output/app.jar

FROM  openjdk:17-alpine

RUN mkdir /app
WORKDIR /app

COPY --from=builder /output/app.jar /app/app.jar

#TODO:: to jest komensat która startowała apkę zrobioną z grejdlem, czy dla mejwena będzie inna to nei wiem
ENTRYPOINT /bin/sh -c 'umask 0002 && java -Djava.security.egd=file:/dev/./urandom -XX:+UseG1GC ${DEBUG_ARGS} -jar app.jar'
