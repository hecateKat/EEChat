FROM maven:3.8-openjdk-17 as builder

RUN mkdir /app && mkdir /output
WORKDIR /app

COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY . .
RUN mvn clean -e -B package

ARG JAR_FILE=/app/target/*.war
RUN mv ${JAR_FILE} /output/app.war

FROM quay.io/wildfly/wildfly

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin

COPY --from=builder /output/app.war /opt/jboss/wildfly/standalone/deployments/app.war

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]