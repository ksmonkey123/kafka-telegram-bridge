FROM openjdk:17-alpine
COPY target/kafka_telegram_bridge.jar kafka_telegram_bridge.jar
ENTRYPOINT ["java", "-jar", "kafka_telegram_bridge.jar"]