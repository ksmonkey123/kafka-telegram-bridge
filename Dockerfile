FROM eclipse-temurin:21
COPY target/kafka_telegram_bridge.jar kafka_telegram_bridge.jar
ENTRYPOINT ["java", "-jar", "kafka_telegram_bridge.jar"]