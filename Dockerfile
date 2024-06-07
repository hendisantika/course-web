FROM azul/zulu-openjdk-alpine:21.0.3-21.34-arm64
LABEL authors="hendisantika"
COPY target/*.jar app.jar
ENTRYPOINT exec java $JAVA_AGENT $JAVA_OPTS $JMX_OPTS -jar app.jar
