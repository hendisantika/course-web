FROM eclipse-temurin:25-jre-alpine
LABEL authors="hendisantika"
COPY target/*.jar app.jar
ENTRYPOINT exec java $JAVA_AGENT $JAVA_OPTS $JMX_OPTS -jar app.jar
